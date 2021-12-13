<html>
<div>
  <h3>接口名称: <span th:text="${doc.apiName}">未渲染成功</span></h3>
  <h3>所属模块: <span th:text="${doc.modelName}">未渲染成功</span></h3>
  <h3>接口路径: <span th:text="${doc.path}">未渲染成功</span></h3>
  <h3>接口路径: <span th:text="${doc.requestMethod}">未渲染成功</span></h3>
  <h3>参数格式: <span th:text="${doc.paramType}">未渲染成功</span></h3>
</div>
<br/>
<div>
  <h3>
    请求参数
  </h3>
  <table>
    <thead>
    <tr>
      <th>参数名称</th>
      <th>参数描述</th>
      <th>参数类型</th>
      <th>参数位置</th>
      <th>是否必要</th>
    </tr>
    </thead>
    <tbody>
    <tr th:each="parameter: ${doc.parameters}">
      <td th:text="${parameter.name}">未渲染成功</td>
      <td th:text="${parameter.desc}">未渲染成功</td>
      <td th:text="${parameter.type}">未渲染成功</td>
      <th th:text="${parameter.position}">未渲染成功</th>
      <th th:text="${parameter.required}">未渲染成功</th>
    </tr>
    </tbody>
  </table>
</div>
<br/>
<div>
  <h3>
    请求体 Request body (application/json)
  </h3>
  <div th:if="${#lists.isEmpty(doc.requestBodys)}">
    <br/>
    不用传请求体
  </div>
  <br/>
  <div th:each="body:${doc.requestBodys}">
    <br/>
    <table>
      <thead>
      <tr>
        <th th:text="${body.name}" colspan="3">未渲染成功</th>
      </tr>
      <tr>
        <th>参数名称</th>
        <th>参数描述</th>
        <th>参数类型</th>
      </tr>
      </thead>
      <tbody>
      <tr th:each="bodys : ${body.bodys}">
        <td th:text="${bodys.name}">未渲染成功</td>
        <td th:text="${bodys.desc}">未渲染成功</td>
        <td th:text="${bodys.type}">未渲染成功</td>
      </tr>
      </tbody>
    </table>
  </div>

</div>
<br/>
<br/>
<div>
  <h3>
    请求示例
  </h3>
  <br/>
  <ac:structured-macro ac:name="code">
    <ac:plain-text-body>
      <![CDATA[参数示例]]>
    </ac:plain-text-body>
  </ac:structured-macro>
</div>
<br/>
<div>
  <h3>
    返回参数
  </h3>

  <div th:if="${#lists.isEmpty(doc.responseBodys)}">
    <br/>
    没有返回参数
  </div>
  <br/>
  <div th:each="body:${doc.responseBodys}">
    <br/>
    <table>
      <thead>
      <tr>
        <th th:text="${body.name}" colspan="3">未渲染成功</th>
      </tr>
      <tr>
        <th>参数名称</th>
        <th>参数描述</th>
        <th>参数类型</th>
      </tr>
      </thead>
      <tbody>
      <tr th:each="bodys : ${body.bodys}">
        <td th:text="${bodys.name}">未渲染成功</td>
        <td th:text="${bodys.desc}">未渲染成功</td>
        <td th:text="${bodys.type}">未渲染成功</td>
      </tr>
      </tbody>
    </table>
  </div>
  <br/>
</div>
<br/>
<div>
  <h3>返回示例</h3>
  <br/>
  <ac:structured-macro ac:name="code">
    <ac:plain-text-body>
      <![CDATA[参数示例]]>
    </ac:plain-text-body>
  </ac:structured-macro>
</div>
</html>

