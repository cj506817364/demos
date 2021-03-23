Vue.config.silent = false;
var imgBaseUrl = common.getImgUrl("jpg");

//交叉检索组件
var w_category = Vue.extend({
    template: '#w_category',
    data: function () {
        return {
            cgList: common.cgList,
            cgData: {},
            crossResults: []
        }
    },
    props: ['serviceGoodsResults'],
    watch: {
        serviceGoodsResults: function () {
            this.getCrossResults();
        }
    },
    methods: {
        //整理交叉检索数据
        getCrossResults: function (data) {
            var serviceGoodsResults = this.serviceGoodsResults;
            // var goods = searchListVUE.selectedServiceGoods.split(",");
            if(searchCache.get('click_ids') === false){
                return;
            }
            var goods = searchCache.get('click_ids').split(",");
            var arr = [];
            goods.map(function (key) {
                if(key != ""){
                    var obj = {};
                    obj['name'] = key;
                    obj['data'] = serviceGoodsResults[key];
                    arr.push(obj);
                }
            });
            arr.reverse();
            this.crossResults = arr;
            $('.cross-range-container').animate({scrollTop:0}, 200);
        },
        //删除交叉检索小项
        deleteCrossItem: function (e) {
            var good = e.target.dataset.value;
            value = good.split("_")[1];
            $("[data-cgname="+value+"]").removeClass("selected");

            var click_ids = searchCache.get('click_ids');
            if(!click_ids){
                click_ids = '';
            }
            click_ids = click_ids.replace(good + ',', '');
            searchCache.cache('click_ids', click_ids);

            searchListVUE.getCrossResults();
        },
        //尼斯分类排序
        sortNice: function(alldata){
            //数据数组重排
            var frontData = [];
            var middleData = [];
            var backData = [];
            var newData = [];
            for(var i = 0; i < alldata.length; i++){
                var d_fcgnum = alldata[i]['fcgnum'];
                if(!isNaN(d_fcgnum[0]) && d_fcgnum != "" && d_fcgnum != " "){
                    frontData.push(alldata[i]);
                }else if(d_fcgnum[0] == "C"){
                    middleData.push(alldata[i]);
                }else{
                    backData.push(alldata[i]);
                }
            }
            alldata = frontData.concat(middleData, backData);
            return alldata;
        },
        //搜索判断是否已存在
        checkSecondSm: function () {
            var click_ids = searchCache.get('click_ids');
            if(click_ids === false) return;
            click_arr = click_ids.split(",");
            click_arr.map(function (id) {
                if(id != ''){
                    var cgname = id.split("_")[1];
                    $(".group-left .second-sm .row[data-cgname='"+cgname+"']").addClass("selected");
                }
            });
        },
        //获取尼斯分类
        getCgList: function (e) {
            var _this = this;
            var pid = e.target.dataset.cgid;
            var params = {
                pid:pid
            };
            var method = 'search/get-cg-list';
            common.ajax(params, method, function (res) {
                if(res.status){
                    _this.cgData[pid] = res.msg;
                }
                console.log(_this.cgData);
            })
        }
    }

});
var searchListVUE = new Vue({
    el: "#searchList",
    data: {
        test: 0,
        //请求参数
        params: {
            key: key,
            param: param,
            page: 0,
            pageSize: 20,
            styles: styles,
            typeCode: "",
            processCode3: "",
            createYear: "",
            groupFilter: '',
            serviceGoods: '', //交叉检索群组
            advanceFilter: '',  //高级筛选
            dataIds: [],
            appkey: appkey,
            host: host
        },
        data: {},
        filter_key: ['----'],
        imgBaseUrl: imgBaseUrl,
        key: key,
        serviceGroup: serviceGroupMapData,   //大类群组
        searchWay: common.searchWay,
        selectedStyles: styles.split(","),
        selectedGraphCode: '',  //图形要素
        selectedSimilarCode: '',
        selectedStandardCode: '',
        selectedApplicants: '',  //选择申请人
        selectedCategories: typeCode,
        searchCategories: typeCode,   //搜索用申请类别
        selectedLawStatus: processCode3,
        selectedCreateYears: createYear,
        selectedHighFilter: highFilter,
        selectedServiceGoods: '',
        graphCode: [],
        similarCode: [],
        standardCode: [],
        applicants: [],
        cgList: common.cgList,   //申请类别
        cgNoItemNum: 0,   //结果为空的申请类别数量
        lawStatus: [],  //法律状态
        createYears: [],  //申请年份
        highFilter: [
            {'name':'chiming','name_zh':'驰名商标','checked': false, 'fromType': 'highFilter'},
            {'name':'zhuming','name_zh':'著名商标', 'checked': false, 'fromType': 'highFilter'},
            {'name':'dili','name_zh':'地理标志', 'checked': false, 'fromType': 'highFilter'}
        ],  //高级筛选
        chooseIds: '',  //已选搜索条件
        chooseList: [],
        resultList: [],   //搜索列表
        groupList: [], //群组列表
        groupFilter: {},  //选择群组对象
        groups: [],   //每一大类群组
        catename: {},
        categoryListData: [],
        faceListData: [],
        serviceGoodsResults: [],   //交叉检索结果
        totalResults: 0,
        pages: 0,
        showHelp: 1
    },
    watch: {
        resultList: function () {
            var ids = this.selectedGraphCode + ',' +
                this.selectedSimilarCode + ',' +
                this.selectedStandardCode + ',' +
                this.selectedApplicants + ',' +
                this.selectedLawStatus + ',' +
                this.selectedCreateYears + ',' +
                this.selectedHighFilter;

            this.chooseIds = ids;
            //神策筛选条件
            this.clickScreeningConditions();
        },
        selectedStyles: function () {
            this.getSearchWay();
        }
    },
    created: function () {
        this.getSearchWay('init');
        this.init();
        this.initChooseList();
    },
    methods: {
        init: function () {
            var noHelp = localStorage.noHelp;
            if(noHelp != null){
                if(localStorage.noHelp.indexOf(userId) != -1){
                    this.showHelp = 0
                }
            }else{
                this.showHelp = 1
            }
            this.search('all',1);
        },
        initChooseList: function () {
            var _this = this;
            var chooseIds = this.selectedGraphCode + ',' +
                this.selectedSimilarCode + ',' +
                this.selectedStandardCode + ',' +
                this.selectedApplicants + ',' +
                this.selectedLawStatus + ',' +
                this.selectedCreateYears + ',' +
                this.selectedHighFilter;

            this.chooseIds = chooseIds;
            this.highFilter.map(function (item) {
                if(chooseIds.indexOf(item['name']) != -1){
                    _this.chooseList.push({
                        name: item['name'],
                        name_zh: item['name_zh'],
                        fromType: item['fromType']
                    })
                }
            })
        },
        //设置参数
        setParams: function (searchWay) {
            if(this.key != ''){
                this.params.key = this.key;
            }else{
                // this.params.key = '----';
            }

            if (searchWay == 'all') {
                this.params.graphcode = this.selectedGraphCode;
                this.params.similar_code = this.selectedSimilarCode;
                this.params.standard_code = this.selectedStandardCode;
                this.params.applicant = this.selectedApplicants;
                this.params.typeCode = this.searchCategories;
                this.params.processCode3 = this.selectedLawStatus;
                this.params.createYear = this.selectedCreateYears;
                this.params.serviceGoods = this.selectedServiceGoods;
                this.params.advanceFilter = this.selectedHighFilter;
            } else {
                this.params.similar_code = '';
                this.params.standard_code = '';
                this.params.applicant = '';
                this.params.typeCode = '';
                this.params.processCode3 = '';
                this.params.createYear = '';
                this.params.groupFilter = '';
                this.params.serviceGoods = '';
                this.params.advanceFilter = '';
            }
        },
        //获取数据
        getData: function (callback) {
            var _this = this;
            var params = this.params;
            params['groupFilter'] = getGroupFilters(params);
            var keyWords = this.params.key;
            var method = "search/search-list";
            if (this.params.key != '') {
                var loading = layer.load(2);
                common.ajax(params, method, function (res) {
                    if (res.status) {
                        layer.close(loading);
                        var userip = res.data.userip;
                        var appkeys = res.data.appkeys;
                        var yanzflag = res.data.yanzflag;
                        var noLogin = res.data.noLogin;
                        geeTest(userip, appkeys, keyWords);
                        if (yanzflag || noLogin) {
                            showLogin();
                        } else {
                            callback(res);
                        }
                    } else {
                        layer.alert(res.msg, function () {
                            location.href = res.data;
                        })
                    }
                }, function (res) {
                    console.log(res);
                })
            }
            //整理群组信息
            function getGroupFilters(params){
                if(params['groupFilter'] == "") { return "" };
                var typeCode = params['typeCode'].split(",");
                var groupFilter = JSON.parse(params['groupFilter']);
                for(var key in groupFilter){
                    if(typeCode.indexOf(key) == -1){
                        delete groupFilter[key];
                    }
                }
                return JSON.stringify(groupFilter);
            }
        },
        //输入框搜索
        inputSearch: function () {
            var keyWords = encodeURIComponent(this.key);
            if(keyWords == ''){
                location.href = '/';
                return;
            }

            var param = this.params.param;
            sc.search_click(function () {
                location.href = '?key=' + keyWords + '&param=' + param;
            });
        },
        //搜索
        search: function (searchWay, saveGroup) {
            this.setParams(searchWay);
            var _this = this;

            var obj = this.params;
            obj.typeCode = this.searchCategories;
            //在获取数据之前先拿到当前的页码， 每个类别可能都有属于自己的页码

            var pageNum = searchCache.getPageNum(obj.typeCode);
            if (pageNum) _this.params.page = pageNum; else _this.params.page = 0;

            var key = searchCache.generateCacheKey(obj);
            var cacheData = searchCache.get(key);
            if (cacheData != false) {
                setData(_this, cacheData);
            } else {
                this.getData(function (res) {
                    var data = res.data;
                    setData(_this, data);
                    //设置缓存
                    _this.setResultListCache(data);
                })
            }

            //渲染数据
            function setData(_this, data) {
                _this.data = data.data;
                _this.catename = data.catename;
                _this.pages = _this.data.pages;
                _this.totalResults = _this.data.totalResults;
                if (_this.params.param == 9) {
                    _this.getGraphCode();
                }
                if (_this.params.param == 4) {
                    _this.getSimilarCode();
                    _this.getStandardCode();
                }
                if (_this.params.param == 3) {
                    _this.getApplicants();
                }
                _this.getCategories();
                _this.getLawStatus();
                _this.getSearchResult();
                _this.getCreateYears();
                _this.getHighFilter();
                if (saveGroup == 1) {
                    var gl = _this.groupList;
                    var dataGl = _this.data.groupList;
                    if(dataGl != null){
                        var new_key = Object.keys(dataGl).toString();
                        if (!gl.hasOwnProperty(new_key)) {
                            gl = Object.assign(gl, dataGl);
                        }
                        _this.groupList = gl;
                    }
                }
            }
        },
        //搜索方式
        getSearchWay: function () {
            // if(styles == ""){
            //     this.searchWay.map(function (item) {
            //         item['checked'] = true;
            //     });
            // }
            var selectedStyles = this.selectedStyles;
            this.searchWay.map(function (item) {
                item['checked'] = false;
                if(selectedStyles.indexOf(item['value']) != -1){
                    item['checked'] = true;
                }
            });
            this.selectedStyles = selectedStyles;
            // this.getSearchWay();
            this.params.styles = selectedStyles.join(",");
            if(arguments[0] != 'init'){
                this.search('all');
            }
        },
        getGraphCode: function () {
            var graphCode = [];
            var cate = this.data.categoryList;
            for (var i = 0; i < cate.length; i++) {
                if (cate[i]['name'] == 'graph_code') {
                    graphCode = cate[i]['facetList'];
                    break;
                }
            }
            this.graphCode = graphCode;
            this.graphCode.map(function (item) {
                item['fromType'] = 'graphCode';
            })
        },
        //获取非规范
        getSimilarCode: function () {
            var faceListData = this.data.facets;
            var similarCode = [];
            for (var i = 0; i < faceListData.length; i++) {
                if (faceListData[i].name == 'similar_code') {
                    similarCode = faceListData[i].facetList;
                    break;
                }
            }
            this.similarCode = similarCode;
            this.similarCode.map(function (item) {
                item['fromType'] = 'similarCode';
            })
        },
        //获取规范商品
        getStandardCode: function () {
            var faceListData = this.data.facets;
            var standardCode = [];
            for (var i = 0; i < faceListData.length; i++) {
                if (faceListData[i].name == 'standard_code') {
                    standardCode = faceListData[i].facetList;
                    break;
                }
            }
            this.standardCode = standardCode;
            this.standardCode.map(function (item) {
                item['fromType'] = 'standardCode';
            })
        },
        //获取申请人
        getApplicants: function () {
            var faceListData = this.data.facets;
            var applicants = [];
            for (var i = 0; i < faceListData.length; i++) {
                if (faceListData[i].name == 'applicant_s') {
                    applicants = faceListData[i].facetList;
                    break;
                }
            }
            this.applicants = applicants;
            this.applicants.map(function (item) {
                item['fromType'] = 'applicants';
            })
        },
        //获取申请类别
        getCategories: function () {
            var noItemNum = 0;
            var faceListData = this.data.facets;
            var typeCode = [];
            for (var i = 0; i < faceListData.length; i++) {
                if (faceListData[i].name == 'type_code') {
                    typeCode = faceListData[i].facetList;
                    break;
                }
            }
            var cgList = common.cgList;
            for (var i = 0; i < cgList.length; i++) {
                cgList[i]['num'] = 0;
                cgList[i]['checked'] = false;
                cgList[i]['show'] = false;
                for (var j = 0; j < typeCode.length; j++) {
                    if (cgList[i]['name'] == typeCode[j]['name']) {
                        cgList[i]['num'] = typeCode[j]['value'];
                        cgList[i]['show'] = true;
                    }
                }
                if(cgList[i]['num'] == 0){
                    noItemNum++;
                }
            }
            this.cgNoItemNum = noItemNum;
            var selectedCategoriesArr = [];
            if (this.selectedCategories != '') {
                selectedCategoriesArr = this.selectedCategories.split(',');
                for (var i = 0; i < selectedCategoriesArr.length; i++) {
                    if (selectedCategoriesArr[i] != "") {
                        cgList[selectedCategoriesArr[i] - 1]['checked'] = true;
                    }
                }
            }
            this.cgList = [];
            this.cgList = cgList;

        },
        //获取法律状态
        getLawStatus: function () {
            var lawStatus1000 = [];
            var lawStatus2000 = [];
            var lawStatus = this.data.categoryList;
            for (var i = 0; i < lawStatus.length; i++) {
                if (lawStatus[i].name == '1000') {
                    lawStatus1000 = lawStatus[i].facetList;
                } else if (lawStatus[i].name == '2000') {
                    lawStatus2000 = lawStatus[i].facetList;
                }
            }
            this.lawStatus = lawStatus1000.concat(lawStatus2000);
            this.lawStatus.map(function (item) {
                item['fromType'] = 'lawStatus';
            })
        },
        //获取申请年份
        getCreateYears: function () {
            var faceListData = this.data.facets;
            for (var i = 0; i < faceListData.length; i++) {
                if (faceListData[i].name == 'create_year') {
                    this.createYears = faceListData[i].facetList;
                    break;
                }
            }
            this.createYears.map(function (item) {
                item['fromType'] = 'createYears';
            })
        },
        //获取高级筛选条件
        getHighFilter: function () {
            var selectedHighFilter = this.selectedHighFilter;
            this.highFilter.map(function (item) {
                if(selectedHighFilter.indexOf(item['name']) != -1){
                    item['checked'] = true;
                }
            })
        },
        //获取搜索结果
        getSearchResult: function () {
            var _this = this;
            var params = {};
            var dataIds = [];
            var resultList = this.data.items;
            for (var i = 0; i < resultList.length; i++) {
                var statusName = resultList[i].statusName;
                if (statusName == null) {
                    resultList[i]['statusName'] = '';
                }
                dataIds[i] = resultList[i].dataId;
                var flag_binary = resultList[i]['typeFlag'].toString(2);
                flag_binary = (Array(3).join('0') + flag_binary).slice(-3);
                resultList[i]['typeFlag'] = flag_binary;
            }
            if(dataIds.length == 0){
                _this.resultList = resultList;
                return false;
            }
            params.dataIds = dataIds;
            var method = 'search/hash-code';
            common.ajax(params, method, function (res) {
                if(res.status){
                    var dataIdArr = res.data;
                    for(var i=0;i<dataIdArr.length;i++){
                        resultList[i].dataIdCode = dataIdArr[i];
                    }
                    _this.resultList = resultList;
                }
            });
        },
        //
        selectStyles: function (e) {
            var selectedStyles = this.selectedStyles;
            var className = e.currentTarget.className;
            if(className.indexOf('checkbox-all') != -1){
                selectedStyles = [];
                if(e.target.checked){
                    this.searchWay.map(function (item) {
                        selectedStyles.push(item['value']);
                    })
                }
            }
            this.selectedStyles = selectedStyles;

        },
        //选择搜索方式
        selectSearchType: function (way) {
            this.params.param = way;
            this.inputSearch();
        },
        //选择群组
        selectGroup: function (e) {
            var value = e.target.dataset.value;
            var $this = $(e.currentTarget);
            var checked = $this.parents(".selected-service-box").find('.checkbox').not('.checkbox-all').find('input:checked');
            var groups = [];
            checked.each(function (i) {
                groups.push($(this).val());
            });
            var groupFilter = this.groupFilter;
            groupFilter[value] = groups;
            if (checked.length == 0) {
                delete groupFilter[value];
            }
            this.groupFilter = groupFilter;
            this.params.groupFilter = JSON.stringify(this.groupFilter);
            this.search('all');
        },
        //取消群组
        clearGroup: function (e) {
            var value = e.target.dataset.value;
            var $this = $(e.currentTarget);
            $this.parents(".selected-service-box").find('.checkbox').removeClass('checked');
            $this.parents(".selected-service-box").find('.checkbox').find('input').removeAttr('checked');
            var groupFilter = this.groupFilter;
            delete groupFilter[value];
            this.groupFilter = groupFilter;
            this.params.groupFilter = JSON.stringify(this.groupFilter);
        },
        //选择已选条件
        selectSearchCategories: function (e) {
            var value = e.target.dataset.value;
            var this_value = this.searchCategories;
            var need_selected = false;
            if (this_value.indexOf(',') != -1 || this_value != value) {
                need_selected = true;
            }
            if (!need_selected) {
                this.searchCategories = this.selectedCategories;
            } else {
                this.searchCategories = value;
            }
            this.search('all');
        },
        //删除已选条件
        deleteSearchCategories: function (e) {
            var value = e.target.dataset.value;
            var ids = this.selectedCategories;
            ids = ids.replace(value + ',', '');
            this.selectedCategories = ids;
            this.searchCategories = this.selectedCategories;
            this.getCategories();
            this.search('all');
        },
        //选择图形要素
        selectGraphCode: function (e) {
            var value = e.target.dataset.value;
            var name = e.target.dataset.name;
            if(this.selectedGraphCode != '' && this.selectedGraphCode == value){
                this.selectedGraphCode = '';
            } else {
                this.selectedGraphCode = value;
            }
            this.search('all');
            this.getChooseList({
                id: value,
                name: name,
                from: 'graphCode'
            });
        },
        //选择非规范商品
        selectSimilarCode: function (e) {
            var value = e.target.dataset.value;
            var name = e.target.dataset.name;
            if(this.selectedSimilarCode != '' && this.selectedSimilarCode == value){
                this.selectedSimilarCode = '';
            } else {
                this.selectedSimilarCode = value;
            }
            this.search('all');
            this.getChooseList({
                id: value,
                name: name,
                from: 'similarCode'
            });
        },
        //选择规范商品
        selectStandardCode: function (e) {
            var value = e.target.dataset.value;
            var name = e.target.dataset.name;
            if(this.selectedStandardCode != '' && this.selectedStandardCode == value){
                this.selectedStandardCode = '';
            } else {
                this.selectedStandardCode = value;
            }
            this.search('all');
            this.getChooseList({
                id: value,
                name: name,
                from: 'standardCode'
            });
        },
        //选择申请人
        selectApplicants: function (e) {
            var value = e.target.dataset.value;
            var name = e.target.dataset.name;
            if(this.selectedApplicants != '' && this.selectedApplicants == value){
                this.selectedApplicants = '';
            } else {
                this.selectedApplicants = value;
            }
            this.search('all');
            this.getChooseList({
                id: value,
                name: name,
                from: 'applicants'
            });
        },
        //选择申请类别
        selectCategories: function (e) {
            var value = e.target.dataset.value;
            var cgList = this.cgList;
            var isCancel = 0;   //是否是取消
            this.selectedCategories = '';
            for (var i = 0; i < cgList.length; i++) {
                if (cgList[i].name == value) {
                    cgList[i]['checked'] = !cgList[i]['checked']
                    if(!cgList[i]['checked']){
                        isCancel = 1
                    }
                }
                if (cgList[i]['checked']) {
                    this.selectedCategories += cgList[i].name + ',';
                }
            }
            this.cgList = [];
            this.cgList = cgList;
            // this.searchCategories = this.selectedCategories;
            if(this.selectedCategories == ''){
                this.searchCategories = '';
            }else{
                if(isCancel != 1){
                    this.searchCategories = value;
                }else{
                    this.searchCategories = this.selectedCategories;
                }
            }
            getSimilarBrand();  //相似商标
            this.search('all', 1);
        },
        //选择法律状态
        selectLawStatus: function (e) {
            var value = e.target.dataset.value;
            var name = e.target.dataset.name;
            if (this.selectedLawStatus != '' && this.selectedLawStatus == value) {
                this.selectedLawStatus = '';
            } else {
                this.selectedLawStatus = value;
            }
            this.search('all');
            this.getChooseList({
                id: value,
                name: name,
                from: 'lawStatus'
            });
        },
        //选择申请年份
        selectCreateYears: function (e) {
            var value = e.target.dataset.value;
            var name = e.target.dataset.name;
            if (this.selectedCreateYears != '' && this.selectedCreateYears == value) {
                this.selectedCreateYears = '';
            } else {
                this.selectedCreateYears = value;
            }
            this.search('all');
            this.getChooseList({
                id: value,
                name: name,
                from: 'createYears'
            });
        },
        //选择交叉检索
        selectHighFilterCross: function (e) {
            var value = e.target.dataset.value;
            var name = e.target.dataset.name;
            if(this.selectedHighFilter == 'cross'){
                this.closeCrossCheck();
            }else{
                this.selectedHighFilter = 'cross';
                this.getCrossResults();
            }
        },
        //选择高级筛选
        selectHighFilter: function (e) {
            var _this = this;
            var value = e.target.dataset.value;
            var name = e.target.dataset.name;
            if(this.selectedHighFilter == 'cross') {
                this.closeCrossCheck(selectFilter);
            }else{
                selectFilter();
            }
            function selectFilter() {
                _this.selectedHighFilter = '';
                var highFilter = _this.highFilter;
                for (var i = 0; i < highFilter.length; i++) {
                    if (highFilter[i]['name'] == value) {
                        highFilter[i]['checked'] = !highFilter[i]['checked']
                    }
                    if (highFilter[i]['checked']) {
                        _this.selectedHighFilter += highFilter[i]['name'] + ',';
                    }
                }
                _this.search('all');
                _this.getChooseList({
                    id: value,
                    name: name,
                    from: 'highFilter',
                    type: 'multiple'
                });
            }
        },
        //关闭弹框判断
        closeCrossCheck: function (callback) {
            var _this = this;
            if(callback == null){
                callback = function () {}
            }
            if(this.selectedServiceGoods != '' ){
                layer.confirm('是否对所选内容进行交叉检索？', function () {
                    _this.selectedHighFilter = '';
                    _this.search('all');
                    layer.closeAll();
                    callback();
                }, function () {
                    _this.selectedHighFilter = '';
                    layer.closeAll();
                    callback();
                })
            }else{
                _this.selectedHighFilter = '';
                callback();
            }
        },
        //获取交叉检索结果
        getCrossResults: function () {
            var _this = this;
            var ids = '';
            // $(".group-left .list .second-sm .row.selected").each(function (i) {
            //     var group_num = $(this).parents(".title-second").find(".second-lg").attr("data-cgnum");
            //     var cgname = $(this).attr("data-cgname");
            //     ids += group_num + '_' + cgname + ',';
            // });
            ids = searchCache.get('click_ids');
            if(ids === false) return;
            var method = "search/get-intersect-scope";
            var params = {'group_goods': ids};

            //数据缓存
            var key = ids;
            var cacheData = searchCache.get(key);
            if(cacheData){
                _this.serviceGoodsResults = cacheData;
            }else{
                var loading = layer.load(2);
                common.ajax(params, method, function (res) {
                    if(res.status){
                        _this.serviceGoodsResults = res.data;
                        searchCache.cache(key, res.data);
                    }
                    layer.close(loading);
                });
            }

            this.selectedServiceGoods = ids;
        },
        //搜索结果缓存
        setResultListCache: function (data) {
            var obj = this.params;
            var key = searchCache.generateCacheKey(obj);
            searchCache.cache(key, data);
        },
        //获取搜索条件
        getChooseList: function (obj) {
            var _this = this;
            var chooseArr = this.chooseIds.split(",");
            var o = {};
            o['name'] = obj['id'];
            o['name_zh'] = obj['name'];
            o['fromType'] = obj['from'];
            var chooseList = this.chooseList;
            var hasObj = false;
            chooseList.map(function (item, i) {
                if(item['name'] == o['name'] && item['fromType'] == o['fromType']){
                    console.log(chooseList);
                    chooseList.splice(i, 1);
                    hasObj = true;
                }
                if(item['name'] != o['name'] && item['fromType'] == o['fromType'] && obj['type'] != 'multiple'){
                    item = o;
                    hasObj = true;
                }
            });

            if(!hasObj){
                this.chooseList.push(o);
            }else {
            };
        },
        //删除搜索条件
        deleteChooseId: function (e) {
            var _this = this;
            var id = e.target.dataset.value;
            var fromType = e.target.dataset.type;

            this.chooseList.map(function (item, i) {
                if(item['name'] == id && item['fromType'] == fromType){
                    _this.chooseList.splice(i, 1);
                }
            });

            switch (fromType) {
                case 'graphCode':
                    _this.selectedGraphCode = '';
                    break;
                case 'similarCode':
                    _this.selectedSimilarCode = '';
                    break;
                case 'standardCode':
                    _this.selectedStandardCode = '';
                    break;
                case 'applicants':
                    _this.selectedApplicants = '';
                    break;
                case 'lawStatus':
                    _this.selectedLawStatus = '';
                    break;
                case 'createYears':
                    _this.selectedCreateYears = '';
                    break;
                case 'highFilter':
                    this.highFilter.map(function (item) {
                        if(item['name'] == id){
                            item['checked'] = false;
                        }
                    });
                    _this.selectedHighFilter = _this.selectedHighFilter.replace(id + ',', '');
                    break;
                case 'cross':
                    _this.selectedServiceGoods = '';
                    break;
            }
            _this.chooseIds = _this.chooseIds.replace(id + ',', '');
            _this.search('all');
        },
        //翻页
        goPage: function () {
            searchCache.cachePageNum(searchListVUE.searchCategories, this.params.page);
            this.search('all')
        },

        // 神策
        // 查看商标详情
        goDetail: function (e) {
            var index = Number(e.currentTarget.dataset.index);
            var _this = this;
            var brand = _this.resultList[index];
            var id = brand['id'];
            var flag = brand['typeFlag'];
            console.log(brand);
            sc.click_events(sc.events.searchDetailsClick, {
                brandName: brand['name'],
                brandApplyNumber: brand['dataId'],
                brandType: cgList[brand.typeCode - 1].value,
                brandStatus: brand['processName']
            });
            window.open('/index/searchdetail/' + id + '.html?type_flag=' + flag);
        },
        // 商品分类
        goNice: function () {
            var urlPath = location.href;
            var searchName = this.key;
            sc.click_events(sc.events.tradeMarkSearchClickNice, {
                urlPath: urlPath,
                isLogin: isLogin,
                searchName: searchName
            });
        },
        // 初审公告
        goNotice: function () {
            var urlPath = location.href;
            var searchName = this.key;
            sc.click_events(sc.events.tradeMarkSearchClickPublication, {
                urlPath: urlPath,
                isLogin: isLogin,
                searchName: searchName
            });
        },
        // 筛选条件
        clickScreeningConditions: function () {
            var searchName = this.key;
            var niceClassification = [];
            var stateLaw = this.selectedLawStatus;
            this.lawStatus.map(function (item) {
                if(item['name'] == stateLaw){
                    stateLaw = item['name_zh'];
                    return;
                }
            });
            var year = this.selectedCreateYears;
            this.cgList.map(function (item) {
                if(item['checked']){
                    var str = item['fcgnum'] + item['fcgname'];
                    niceClassification.push(str);
                }
            });
            var isGroupSift = false;
            if(this.params.groupFilter != ''){
                isGroupSift = true;
            }

            var advancedSift = [];
            if(this.params.serviceGoods != ''){
                advancedSift.push("交叉检索");
            }
            this.highFilter.map(function (item) {
                if(item['checked']){
                    advancedSift.push(item['name_zh']);
                }
            });

            sc.click_events(sc.events.TrademarkSearch, {
                searchName: searchName,
                niceClassification: niceClassification,
                isGroupSift: isGroupSift,
                stateLaw: stateLaw,
                year: year,
                advancedSift: advancedSift
            });
        },
        //商标关注
        brandFollow: function (e) {
            var data_id = e.currentTarget.dataset.flag;
            sc.click_events(sc.events.tradeMarkSearchFollow, {
                followTrademarkID: data_id
            });
        },
        //商标购买
        brandBuy: function (e, buyPath) {
            var data_id = e.currentTarget.dataset.dataid;
            sc.click_events(sc.events.tradeMarkSearchBuy, {
                buyTrademarkID: data_id,
                buyPath: buyPath
            });
        },
        //点击后续业务
        brandBusiness: function (index, businessType) {
            var data_id = this.resultList[index]['dataId'];
            sc.click_events(sc.events.tradeMarkSearchBusiness, {
                businessType: businessType,
                trademarkID: data_id
            });
        },
        //商标注册地址
        getBrandRigisterUrl: function (item) {
            if(isAgent){
                return SITE_URL_WWW + 'partner-order/register?brandname=' + key + '&type=' + item.name;
            }else{
                return SITE_URL_WWW + 'product-buy?brandname=' + key + '&type=' + item.name;
            }
        }
    },

    components: {
        'v-page': page,
        'v-nice': w_category
    }
});

//GeeTeST验证
function geeTest(userip, appkey, keyWords) {
    var handler = function (captchaObj) {
        captchaObj.onReady(function () {
            //$("#wait").hide();
        }).onSuccess(function () {
            var result = captchaObj.getValidate();
            if (!result) {
                alert('请完成验证');
                return;
            }
            $.ajax({
                url: '/index/ans',
                type: 'POST',
                dataType: 'json',
                data: {
                    geetest_challenge: result.geetest_challenge,
                    geetest_validate: result.geetest_validate,
                    geetest_seccode: result.geetest_seccode
                },
                success: function (data) {
                    if (data.status) {
                        // var userip = $("#userip").val();
                        // var appkey = $("#appkey").val();
                        var thirdinfo = userip + "." + appkey;
                        var expdate = new Date();   //初始化时间
                        expdate.setTime(expdate.getTime() + 30 * 60 * 1000);   //时间(30分钟)
                        document.cookie = "YZNAME=" + thirdinfo + ";expires=" + expdate.toGMTString() + ";path=/search";
                        noLoginJump();
                    } else if (!data.status) {
                        setTimeout(function () {
                            alert('请完成验证');
                            captchaObj.reset();
                        }, 1500);
                    }
                }
            });
        });

        // 更多接口说明请参见：http://docs.geetest.com/install/client/web-front/
        $(".btn-search").on('click', function () {
            if (!keyWords) {
                alert("请输入关键词后查询！");
                return;
            }
            // 调用之前先通过前端表单校验
            captchaObj.verify();
        });
    };
    $.ajax({
        url: "/index/abc?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {

            // 调用 initGeetest 进行初始化
            // 参数1：配置参数
            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
            initGeetest({
                // 以下 4 个配置参数为必须，不能缺少
                gt: data.gt,
                challenge: data.challenge,
                offline: !data.success, // 表示用户后台检测极验服务器是否宕机
                new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机

                product: "bind", // 产品形式，包括：float，popup
                width: "300px"
                // 更多配置参数说明请参见：http://docs.geetest.com/install/client/web-front/
            }, handler);
        }
    });

    function noLoginJump() {
        var filter_arr = [];
        $(".search .filter .singleCheck .label.checked").each(function () {

            filter_arr.push($(this).attr('data-name'));

        });

        var keyname = keyWords;
        //var type = encodeURI($("#otype").val()).replace(/\+/g, '%2B');
        var jumpUrl = "/index/search";

        jumpUrl += '?param=' + param + '&key=' + keyname;
        if (appkey == null) {
            jumpUrl += '?param=' + param + '&key=' + keyname + '&appkey=' + appkey;
        }
        location.reload();
    }
}

//显示提示登录弹窗
function showLogin(num) {
    $(".check_login_bg").remove();
    var bg_class_name = "c_bg1";
    var login_tips = '<div class="check_login_tips">' +
        '<p>嗯~O(∩_∩)O，做个简单的验证吧！</p>' +
        '<img src="' + SITE_URL + 'home/images/checklogin.png">' +
        '<a class="button btn-search">点击按钮进行验证</a>' +
        '<a class="href" href="' + ONLY_URL + '">登录权大师</a>' +
        '</div>';
    if (num == 1) {
        bg_class_name = "c_bg1";
    }
    if (num == 2) {
        bg_class_name = "c_bg2";
    }

    var img_bg = '<div class="check_login_bg ' + bg_class_name + '"><div class="login_black_bg">' + login_tips + '</div></div>';

    $("body").append(img_bg);
}