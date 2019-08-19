import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-02-20 13:10
 * Description:
 */
public class TestAward {

    // 最大免单金额
    private static final BigDecimal MAXIMUM_FREE_OF_CHARGE_AMOUNT = new BigDecimal(2);

    // 用户最大返奖金额
    private static final BigDecimal MAXIMUM_OPENID_AWARD_AMOUNT = new BigDecimal(100);

    // 商家最大返奖金额
    private static final BigDecimal MAXIMUM_BUSINESS_AWARD_AMOUNT = new BigDecimal(100);

    // 用户最小支付金额
    private static final int MINIMUM_OPENID_PAYMENT_INT = 1;

    private static final BigDecimal MINIMUM_OPENID_PAYMENT_BIG_DECIMAL = new BigDecimal(MINIMUM_OPENID_PAYMENT_INT);

    private static final Random random = new Random();

    // 金额格式化成两位小数
    private static final DecimalFormat moneyFormat = new DecimalFormat("0.00");

    private static final BigDecimal MAXIMUM_REWARD_RATIO = new BigDecimal(0.8);

    private static BigDecimal OPENID_POOL_MONEY = new BigDecimal(0);
    private static BigDecimal BUSINESS_POOL_MONEY = new BigDecimal(0);
    // 获取每次的支付金额
    public static BigDecimal getPayMoney(int max) {
        return MINIMUM_OPENID_PAYMENT_BIG_DECIMAL.add(new BigDecimal(random.nextDouble() * max - MINIMUM_OPENID_PAYMENT_INT));
    }

    public static void main(String[] args) {

        int rgb = new Color(229, 101, 14,255).getRGB();
        System.out.println(rgb);
        // 设置支付次数,总金额,最小支付金额
            List red = getRed(54, 820.56f, 1);

            // 商家参与比例
            BigDecimal businessBaRate = new BigDecimal(0.2);

            BigDecimal openidPoolRate = new BigDecimal(0.5);
            BigDecimal businessPoolRate = new BigDecimal(0.5);

            final int[] i = {0};

            red.forEach(k -> {
                i[0]++;
                BigDecimal payMoney = new BigDecimal((float)k).setScale(2, BigDecimal.ROUND_DOWN);

                BigDecimal joinMoney = payMoney.multiply(businessBaRate).setScale(2, BigDecimal.ROUND_DOWN);

                BigDecimal joinOpenidPoolMoney = joinMoney.multiply(openidPoolRate).setScale(2, BigDecimal.ROUND_DOWN);
                BigDecimal joinBusinessPoolRate = joinMoney.multiply(businessPoolRate).setScale(2, BigDecimal.ROUND_DOWN);

                OPENID_POOL_MONEY = OPENID_POOL_MONEY.add(joinOpenidPoolMoney).setScale(2, BigDecimal.ROUND_DOWN);
                BUSINESS_POOL_MONEY = BUSINESS_POOL_MONEY.add(joinMoney.subtract(joinOpenidPoolMoney));

//                System.out.println("==================================================");
//                System.out.println("第" + i[0] + "次付款");
//                System.out.println("用户支付: " + payMoney + "元,参与分成金额: " + joinMoney + "元");
//                System.out.println("分成前用户奖池剩余: " + OPENID_POOL_MONEY + "元,分成前商家奖池剩余: " + BUSINESS_POOL_MONEY + "元");

                BigDecimal openidAward = getOpenidAward(payMoney, OPENID_POOL_MONEY).setScale(2, BigDecimal.ROUND_DOWN);

                OPENID_POOL_MONEY = OPENID_POOL_MONEY.subtract(openidAward).setScale(2, BigDecimal.ROUND_DOWN);

                BigDecimal businessAward = getBusinessAward(BUSINESS_POOL_MONEY);
                BUSINESS_POOL_MONEY = BUSINESS_POOL_MONEY.subtract(businessAward).setScale(2, BigDecimal.ROUND_DOWN);
                String msg = "";
                if(openidAward.compareTo(payMoney) >= 0){
                    msg = "免单成功!";
                }

//                System.out.println("用户红包: " + openidAward + "元,商家红包: " + businessAward + "元");
//                System.out.println("分成后用户奖池剩余: " + OPENID_POOL_MONEY + "元,分成后商家奖池剩余: " + BUSINESS_POOL_MONEY + "元");
//                System.out.println("是否免单: " + msg);
                System.out.println("第" + i[0] + "次付款 支付金额: "
                        + payMoney + "元,参与分成金额: " + joinMoney + "元,用户红包: "
                        + openidAward + "元,用户奖池剩余: "
                        + OPENID_POOL_MONEY + "元,商家红包: " + businessAward + "元,商家奖池剩余: "
                        + BUSINESS_POOL_MONEY + "元!" + msg);

                if(payMoney.compareTo(openidAward) < 0){
                    System.err.println("error!");
                }
            });

    }

    /**
     * 采用微信红包算发
     * @param number 红包数
     * @param total 红包总额
     * @param min 最小红包
     * @return
     */
    public static List getRed(int number,float total,double min) {

        float money;
        double max;
        int i = 1;
        List math = new ArrayList();
        DecimalFormat df = new DecimalFormat("###.##");
        while (i < number) {
            //保证即使一个红包是最大的了,后面剩下的红包,每个红包也不会小于最小值
            max = total - min * (number - i);
            int k = (int) (number - i) / 2;
            //保证最后两个人拿的红包不超出剩余红包
            if (number - i <= 2) {
                k = number - i;
            }
            //最大的红包限定的平均线上下
            max = max / k;
            //保证每个红包大于最小值,又不会大于最大值
            money = (int) (min * 100 + Math.random() * (max * 100 - min * 100 + 1));
            money = (float) money / 100;
            //保留两位小数
            money = Float.parseFloat(df.format(money));
            total = (int) (total * 100 - money * 100);
            total = total / 100;
            math.add(money);
//            System.out.println("第" + i + "个人拿到" + money + "剩下" + total);
            i++;
            //最后一个人拿走剩下的红包
            if (i == number) {
                math.add(total);
//                System.out.println("第" + i + "个人拿到" + total + "剩下0");
            }
        }
        //取数组中最大的一个值的索引
//        System.out.println("本轮发红包中第" + (math.indexOf(Collections.max(math)) + 1) + "个人手气最佳");
        return math;
    }

        public static void test(BigDecimal payMoney){
        getOpenidAward(payMoney, OPENID_POOL_MONEY);
        System.out.println("支付完成后用户支付金额: " + payMoney + "用户奖池金额: " + OPENID_POOL_MONEY);
    }

    public static BigDecimal getOpenidAward2(BigDecimal payMoney,BigDecimal openidPool){

        // check
        if(payMoney == null || payMoney.compareTo(BigDecimal.ZERO) <= 0){
            return null;
        }

        if(openidPool == null){
            openidPool = BigDecimal.ZERO;
        }

        // if payMoney lt MAXIMUM_FREE_OF_CHARGE_AMOUNT
        if(payMoney.compareTo(MAXIMUM_FREE_OF_CHARGE_AMOUNT) <= 0){
            // 可以实现免单效果
            BigDecimal award = new BigDecimal(new Random().nextDouble() * openidPool.doubleValue());
            // 如果抽奖金额小于用户最小打款金额 未中奖
            if(award.compareTo(MINIMUM_OPENID_PAYMENT_BIG_DECIMAL) < 0){
                return BigDecimal.ZERO;
            }

            if(award.compareTo(payMoney) >= 0){
                return payMoney;
            }

            return award.setScale(2, BigDecimal.ROUND_DOWN);

        }

        // payMoney gt MAXIMUM_FREE_OF_CHARGE_AMOUNT
        BigDecimal award = null;
        if(openidPool.compareTo(payMoney) >= 0){
            award = new BigDecimal(new Random().nextDouble() * payMoney.doubleValue());
        }else {
            award = new BigDecimal(new Random().nextDouble() * openidPool.doubleValue());
        }

        if (award.compareTo(MAXIMUM_OPENID_AWARD_AMOUNT) >= 0){
            return MAXIMUM_OPENID_AWARD_AMOUNT;
        }
        // 如果抽奖金额小于用户最小打款金额 未中奖
        if(award.compareTo(MINIMUM_OPENID_PAYMENT_BIG_DECIMAL) < 0){
            return BigDecimal.ZERO;
        }

        return award.setScale(2, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal getOpenidAward(BigDecimal payMoney,BigDecimal openidPool){

        // check
        if(payMoney == null || payMoney.compareTo(BigDecimal.ZERO) <= 0){
            return null;
        }

        // 如果奖池金额小于等于1元
        if(openidPool == null || openidPool.compareTo(BigDecimal.ONE) == 0){
            return BigDecimal.ZERO;
        }

        // 用于随机的金额
        // if payMoney lt MAXIMUM_FREE_OF_CHARGE_AMOUNT
        if(payMoney.compareTo(MAXIMUM_FREE_OF_CHARGE_AMOUNT) <= 0){
            // 可以实现免单效果
            BigDecimal award = new BigDecimal(new Random().nextDouble() * openidPool.subtract(BigDecimal.ONE).doubleValue()).add(BigDecimal.ONE);
            // 如果抽奖金额小于用户最小打款金额 未中奖
            if(award.compareTo(MINIMUM_OPENID_PAYMENT_BIG_DECIMAL) < 0){
                return BigDecimal.ZERO;
            }

            if(award.compareTo(payMoney) >= 0){
                return payMoney;
            }

            return award.setScale(2, BigDecimal.ROUND_DOWN);

        }

        // payMoney gt MAXIMUM_FREE_OF_CHARGE_AMOUNT
        BigDecimal award = null;
        if(openidPool.compareTo(payMoney) >= 0){
            award = new BigDecimal(new Random().nextDouble() * (payMoney.subtract(BigDecimal.ONE)).doubleValue()).add(BigDecimal.ONE);
        }else {
            award = new BigDecimal(new Random().nextDouble() * (openidPool.subtract(BigDecimal.ONE)).doubleValue()).add(BigDecimal.ONE);
        }

        if (award.compareTo(MAXIMUM_OPENID_AWARD_AMOUNT) >= 0){
            return MAXIMUM_OPENID_AWARD_AMOUNT;
        }
        // 如果抽奖金额小于用户最小打款金额 未中奖
        if(award.compareTo(MINIMUM_OPENID_PAYMENT_BIG_DECIMAL) < 0){
            return BigDecimal.ZERO;
        }

        return award.setScale(2, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal getBusinessAward(BigDecimal businessPool){

        // check
        if(businessPool == null){
            businessPool = BigDecimal.ZERO;
        }

        BigDecimal award = new BigDecimal(new Random().nextDouble() * businessPool.doubleValue());
        if (award.compareTo(MAXIMUM_OPENID_AWARD_AMOUNT) >= 0){
            return MAXIMUM_OPENID_AWARD_AMOUNT;
        }
        // 如果抽奖金额小于用户最小打款金额 未中奖
        return award.setScale(2, BigDecimal.ROUND_DOWN);
    }
}
