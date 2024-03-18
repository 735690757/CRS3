package edu.beihua.KarryCode.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    /*
    *一个名为EmailValidator的类来验证邮箱地址的格式。该类包含一个正则表达式模式EMAIL_PATTERN，
    * 该模式可以验证标准的电子邮件地址格式。然后在类的构造函数中，将该模式编译为一个Pattern对象。
    * 最后，在validate()方法中，将传入的字符串与模式进行匹配，并返回匹配结果。
    * */
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
