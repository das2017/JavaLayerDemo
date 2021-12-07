package org.zzj.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

/**
 *
 *
 */
public class StringUtil extends StringUtils {

    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

    /**
     * @return Random character
     * @author
     */
    public static String random(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    /**
     * @param s       from char
     * @param e       end char
     * @param content original content
     * @param value   replace content
     * @return
     * @description : replace string from s to e
     */
    public static String replaceInValue(String s, String e, String content, String value) {
        if (content == null)
            return null;
        int i = 0;
        int k = 0;
        if ((i = (content.indexOf(s, i))) >= 0 && (k = (content.indexOf(e, k))) >= 0) {
            StringBuffer sbf = new StringBuffer();
            int s_length = s.length();
            int e_length = e.length();
            sbf.append(content.substring(0, i)).append(value);
            int n = i + s_length;
            int m = k + e_length;
            for (; ((i = (content.indexOf(s, n))) >= 0 && (k = (content.indexOf(e, m))) >= 0); ) {
                sbf.append(content.substring(m, i)).append(value);
                n = i + s_length;
                m = k + e_length;
            }
            sbf.append(content.substring(m, content.length()));
            return sbf.toString();
        }
        return content;
    }

    /**
     * @param length  To capture length
     * @param content
     * @return
     * @description : Interception string a top front the length of the
     * character
     */
    public static String cutString(int length, String content) {
        if (content.length() > length)
            return content.substring(0, length);
        return content;
    }

    /**
     * @param sqlString
     * @return
     * @throws Exception
     * @description : Add alias for each arguments in the SQL string
     * @author
     */
    public static String addAliasForSQLArgs(String sqlString, String[] aliases) throws Exception {
        String lowSqlString = sqlString.toLowerCase();

        if (lowSqlString.indexOf("select") == -1 || lowSqlString.indexOf("from") == -1)
            throw new Exception("Illegal SQL statement!");

        String subArgsString = lowSqlString.substring(6, lowSqlString.indexOf("from"));
        String[] args = subArgsString.split(",");

        if (args.length != aliases.length)
            throw new Exception("Arguments length the Aliases's length does not match!");

        StringBuffer argsWithAliases = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i] + " as " + aliases[i];

            argsWithAliases.append(args.length - 1 > i ? args[i] + ", " : args[i] + " ");
        }

        return sqlString.replaceFirst(subArgsString, argsWithAliases.toString());
    }

    /**
     * @param paraMap
     * @param destStr
     * @return
     * @description : string support el tags <code>${contents}</code>
     * @author
     */
    public static String replaceKeysSimple(Map<String, Object> paraMap, String destStr) {
        if (paraMap == null) {
            return destStr;
        }
        Pattern p = Pattern.compile("[{]+\\w+[}]");
        Matcher m = p.matcher(destStr);

        while (m.find()) {
            String k = m.group();
            k = k.substring(1, k.length() - 1);
            for (String key : paraMap.keySet()) {
                if (StringUtil.equals(k, key)) {
                    String value = paraMap.get(key) == null ? "" : paraMap.get(key).toString();
                    key = "{" + key + "}";
                    destStr = destStr.replace(key, value);
                }
            }
        }
        return destStr;
    }

    /**
     * @param target
     * @return
     * @description : support Object and String is null
     */
    public static boolean isNotBlankPlus(Object target) {
        if (target == null)
            return false;

        String targetStr = String.valueOf(target).trim();

        if (isBlank(targetStr))
            return false;

        return !targetStr.replaceAll("\"", "").replaceAll("'", "").equals("null");
    }

    /**
     * @param rules
     * @param target
     * @return
     * @description : Escape function
     * @author
     */
    public static String transferRegex(String rules, String target) {
        if (StringUtil.isBlank(rules) || StringUtil.isBlank(target))
            return target;

        String[] objs = rules.split(",");
        for (int i = 0; i < objs.length; i++) {
            String rule = objs[i].trim();
            target = target.replace(rule, "\\" + rule);
        }

        return target;
    }

    /**
     * @param strSource
     * @param target
     * @param times
     * @return
     * @description : get the time target at strSource
     * @author
     */
    public static int indexOf(String strSource, String target, int times) {
        int result = -1;

        if (isBlank(strSource))
            return result;

        result = 0;
        int temp = 0;
        int count = 1;
        while ((temp = strSource.indexOf(target, temp) + 1) != -1) {
            if (times < count) {
                break;
            }

            if (result <= temp) {
                result = temp;
            } else {
                break;
            }

            count++;
        }

        return result - 1;
    }

    /**
     * @param strSource
     * @param target
     * @param times
     * @return
     * @description : Returns the number of times times the target appeared in
     * strSource
     * @author
     */
    public static int lastIndexOf(String strSource, String target, int times) {
        int result = -1;

        if (isBlank(strSource))
            return result;

        int temp = strSource.length() - 1;
        int count = 1;
        result = strSource.length();

        while ((temp = strSource.lastIndexOf(target, temp)) != -1) {
            if (times < count) {
                break;
            }

            if (result >= temp) {
                result = temp;
            } else {
                break;
            }

            temp--;

            count++;
        }

        if (result == strSource.length())
            return -1;

        return result;
    }

    /**
     * @param strParameter To validate the string
     * @param limitLength  Verification length
     * @return Conform to the length then return true,out of range the return
     * false
     * @description : validate String length, One Chinese char is 3 bit
     */
    public static boolean validateStrByLength(String strParameter, int limitLength) {
        int temp_int = 0;
        byte[] b = strParameter.getBytes();

        for (int i = 0; i < b.length; i++) {
            if (b[i] >= 0) {
                temp_int = temp_int + 1;
            } else {
                temp_int = temp_int + 3;
                i++;
            }
        }

        if (temp_int > limitLength)
            return false;
        else
            return true;
    }

    /**
     * @param s
     * @return
     * @description : Determine whether case letter
     */
    public static boolean isLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!((s.charAt(i) > 'a' && s.charAt(i) < 'z') || (s.charAt(i) > 'A' && s.charAt(i) < 'Z')))
                return false;
        }

        return true;
    }

    public static boolean isBlank(Object value) {
        if (value == null)
            return true;

        if (value instanceof String) {
            if (StringUtil.isBlank((String) value))
                return true;
        }

        return false;
    }

    public static boolean isNotBlank(Object value) {
        return !isBlank(value);
    }

    /**
     * @param regex
     * @param orgi
     * @param pos
     * @return
     * @description :replace Regex
     */
    public static String replaceRegex(String regex, String orgi, int pos) {
        if (isBlank(orgi) || isBlank(regex))
            return orgi;

        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(orgi);

        while (m.find()) {
            String outStr = m.group(pos);
            orgi = orgi.replace(m.group(0), outStr);
        }

        return orgi;
    }

    public static String get(String regex, String orgi, int pos) {
        if (isBlank(orgi) || isBlank(regex))
            return null;

        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(orgi);

        while (m.find()) {
            String outStr = m.group(pos);
            return outStr;
        }

        return null;
    }

    public static boolean in(String[] targets, String value, boolean ignoreCase) {
        if (targets == null)
            return false;

        for (String target : targets) {
            if (ignoreCase) {
                if (value.equalsIgnoreCase(target))
                    return true;
            } else {
                if (value.equals(target))
                    return true;
            }
        }

        return false;
    }

    /**
     * @param value
     * @return
     * @description :compress
     */
    public static String compress(String value) {
        if (isBlank(value))
            return value;

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(value.getBytes());
            gzip.close();
            return out.toString("ISO-8859-1");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * String support like EL Tag <br>
     * <code>
     * Map<String, Object> map = new HashMap<String, Object>();<br><br>
     * <p>
     * Map<String, Object> test = new HashMap<String, Object>();<br>
     * test.put("a", "I'm a�?);<br>
     * map.put("test", test);<br><br>
     * <p>
     * Map<String, Object> test2 = new HashMap<String, Object>();<br>
     * test2.put("b", "I'm b�?);   <br>
     * map.put("test2", test2);<br>
     * <p>
     * String str = "${map.test[a]}";<br>
     * System.out.println(replaceKeys(map, str));<br><br>
     * <p>
     * String str2 = "${map.test[a]}，and more�?{map.test2[b]}";<br>
     * System.out.println(replaceKeys(map, str2));<br>
     * <p>
     * </code>
     *
     * @return
     * @author
     */
    @SuppressWarnings("unchecked")
    public static String replaceKeys(Map<String, Object> paraMap, String destStr) {
        if (paraMap == null)
            return destStr;

        String variableTypeMap = "map";

        for (String key : paraMap.keySet()) {
            String regex = "\\$\\{" + variableTypeMap + "\\." + key + "\\[(.+?)\\]\\}";
            Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(destStr);

            while (m.find()) {
                try {
                    String outStr = m.group(1);
                    Map<String, Object> targetMap = (Map<String, Object>) paraMap.get(key);
                    Object targetValue = targetMap.get(outStr);

                    if (targetValue != null) {
                        destStr = destStr.replace("${" + variableTypeMap + "." + key + "[" + outStr + "]}",
                                targetValue.toString());
                    } else {
                        destStr = "";
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }

        return destStr;
    }

    /**
     * @param source
     * @return
     * @description : trim all string blank
     * @author
     */
    public static String trimBlank(String source) {
        if (isBlank(source)) {
            return "";
        }
        return source.replaceAll(" ", "");
    }

    /**
     * @param source
     * @return
     * @description : form memcached key, key not support space
     * @author
     */
    public static String replaceSpaceToUnderline(String source) {
        if (isBlank(source)) {
            return "";
        }
        return source.replaceAll(" ", "_").toUpperCase();
    }

    /**
     * �?1,2,3"转换�?'1','2','3'"
     *
     * @param string
     * @return
     */
    public static String toStringWithSingleQuotes(String string, String septationString) {
        if (StringUtils.isBlank(string) || StringUtils.isBlank(septationString))
            throw new IllegalArgumentException("string or septationString illegal!");
        String[] strings = string.split(septationString);
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i < strings.length - 1)
                resultString.append("'" + strings[i] + "',");
            else
                resultString.append("'" + strings[i] + "'");
        }
        return resultString.toString();

    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    public static String xssEncode(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append("&gt;");// 全角大于号
                    break;
                case '<':
                    sb.append("&lt;");// 全角小于号
                    break;
                case '\'':
                    sb.append("&lsquo;");// 全角单引号
                    break;
                case '\"':
                    sb.append("&quot;");// 全角双引号
                    break;
                case '&':
                    sb.append("&amp;");// 全角
                    break;
                case '\\':
                    sb.append('＼');// 全角斜线
                    break;
                case '#':
                    sb.append('＃');// 全角井号
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString().replaceAll("script", "scrip*");
    }

    /**
     * 全角字符直接替换成将容易引起xss漏洞的半角字符
     *
     * @param s
     * @return
     */
    public static String xssDecode(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        return s.toString().replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&lsquo;", "\'")
                .replaceAll("&quot;", "\"").replaceAll("&amp;", "&").replaceAll("＼", "\\").replaceAll("＃", "#");
    }

    /**
     * 替换模板内容, 用法如下 <code>
     * Properties p =new Properties();
     * p.put("verify_code", "1234");
     * composeMessage("您好，您的验证码为：$verify_code$", p);
     * </code>
     *
     * @param template
     * @param data
     * @return
     * @throws Exception
     */
    public static String composeMessage(String template, Properties data) throws Exception {
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Object o = it.next();
            template = template.replaceFirst("\\$" + o.toString().split("=")[0] + "\\$", o.toString().split("=")[1]);
        }
        return template;
    }

    public static String composeMessageMap(String template, Map map) throws Exception {
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            template = template.replaceFirst("\\$" + entry.getKey() + "\\$", entry.getValue());
        }
        return template;
    }

    /**
     * @param template //匹配类似velocity规则的字符串 template = "${cat} really needs some
     *                 ${beverage}. ";
     * @param params   被替换关键字的的数据源
     * @return
     */
    public static String composeMessage(String template, Map<String, String> params) {
        String patternString = "\\$\\{(" + StringUtil.join(params.keySet(), "|") + ")\\}";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(template);

        // 两个方法：appendReplacement, appendTail
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, params.get(matcher.group(1)) == null ? "" : params.get(matcher.group(1)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * @param tag
     * @param template //匹配类似velocity规则的字符串 template = "${cat} really needs some
     *                 ${beverage}. ";
     * @param params   被替换关键字的的数据源
     * @return
     */
    public static String composeMessage(String tag,String template, Map<String, String> params) {
        String patternString = tag+"\\{(" + StringUtil.join(params.keySet(), "|") + ")\\}";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(template);

        // 两个方法：appendReplacement, appendTail
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, params.get(matcher.group(1)) == null ? "" : params.get(matcher.group(1)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 转换String数组为Integer数组
     *
     * @param strArray
     * @return
     */
    public static Integer[] toIntegerArray(String[] strArray) {
        Integer[] intArray = null;
        if (strArray.length > 0) {
            intArray = new Integer[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                intArray[i] = Integer.parseInt(strArray[i].trim());
            }
        }
        return intArray;
    }


    /**
     * 函数功能说明 ： 判断对象是否为空. 修改者名字： 修改日期： 修改内容：
     *
     * @param obj
     * @return boolean
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        return !(obj instanceof Number) ? false : false;
    }

    /**
     * 下划线转驼峰法
     *
     * @param line       源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰法转下划线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toLowerCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

    /**
     * 是否是中文
     *
     * @param c
     * @return
     */

    public static boolean isChinese(char c) {

        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION

                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

            return true;

        }

        return false;

    }

    /**
     * 是否是英文
     *
     * @param
     * @return
     */

    public static boolean isEnglish(String charaString) {

        return charaString.matches("^[a-zA-Z]*");

    }

    public static boolean isChinese(String str) {

        String regEx = "[\\u4e00-\\u9fa5]+";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str);

        if (m.find()) {
            return true;
        } else {
            return false;
        }

    }

    public static void main(String[] args) throws Exception {

        Map<String, String> mp = new HashMap<String, String>();
        mp.put("authKey", "abc123!!");
        mp.put("actionType", "saveclue");
        mp.put("name", "张三");
        mp.put("phone", "18744024911");
        mp.put("source", "4");
        mp.put("clueType", "7");
        mp.put("dlrCode", "H2989");
        mp.put("isSendToDLR", "1");
        mp.put("mobileBelongCity", "");
        mp.put("ipLocationCity", "14.23.175.62");
        mp.put("gpsLocationCity", "");
        mp.put("lastDeliveryTime", "");
        mp.put("buyWay", "1");
        mp.put("arrivePlanTime", "1");
        mp.put("activityName", "留资");
        mp.put("smartCode", "");
        mp.put("buyWayCode", "2");
        mp.put("buyPlanTimeCode", "PVECA0000000106");
        mp.put("buyWay", "1");
        mp.put("sex", "1");
        mp.put("pageId", "1");
        /* URLEncoder.encode( */
        System.out.println(new String(JSONObject.toJSONString(mp).getBytes(), "gbk"));
        System.out.println(URLEncoder.encode(JSONObject.toJSONString(mp), "gbk"));
        /*
         * System.out.println(new String("中文".getBytes()).getBytes());
		 * System.out.println(new String("中文".getBytes("iso8859-1"), "utf-8"));
		 */

        StringBuffer url = new StringBuffer("?");
        for (Map.Entry mety : mp.entrySet()) {

            url.append(mety.getKey() + "=" + mety.getValue() + "&");
        }
        System.out.print(timeStrFormat("2017-07-14T22:54:00.000Z"));
    }

    //    2017-07-14T22:54:00.000Z
    public static String timeStrFormat(String timeStr) {
        if (StringUtil.isNotBlank(timeStr)) {
            return timeStr.replace("T", " ").replace(".000Z", "").replace("+08:00","");
        }
        return timeStr;
    }

    /**
     * 获取对象中为null的属性名称
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
