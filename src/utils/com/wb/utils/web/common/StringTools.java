package com.wb.utils.web.common;

import java.util.StringTokenizer;

import org.springframework.util.StringUtils;

public class StringTools extends StringUtils
{
  public static boolean greateThan(String str1, String str2)
  {
    if ((str1 == null) || (str2 == null)) {
      return false;
    }

    return (str1.compareTo(str2) > 0);
  }

  public static boolean lessThan(String str1, String str2)
  {
    return greateThan(str2, str1);
  }

  public static boolean equals(String str1, String str2)
  {
    return ((!(greateThan(str1, str2))) && (!(lessThan(str1, str2))));
  }

  public static String copyByte(String src, int start, int length)
  {
    if (src == null)
      return "";

    byte[] srcData = src.getBytes();

    byte[] data = new byte[length];

    System.arraycopy(srcData, start, data, 0, length);

    return new String(data);
  }

  public static String[] split(String str, String delim)
  {
    StringTokenizer token = new StringTokenizer(str, delim);

    String[] result = new String[token.countTokens()];
    int loop = 0;

    while (token.hasMoreTokens()) {
      result[(loop++)] = token.nextToken();
    }

    return result;
  }

  public static int count(String str, String substr)
  {
    if ((!(hasText(str))) || (!(hasText(substr)))) {
      return 0;
    }

    int count = 0;
    int index = 0;
    int pos = 0;
    int lenOfSubstr = substr.length();

    pos = str.indexOf(substr, index);
    while (pos != -1) {
      ++count;
      index = pos + lenOfSubstr;
      pos = str.indexOf(substr, index);
    }
    return count;
  }

  public static void main(String[] args)
  {
    String str1 = "2";
    String str2 = "3";

    System.out.println(str1 + ">" + str2 + ":" + greateThan(str1, str2));
    System.out.println(str1 + "<" + str2 + ":" + lessThan(str1, str2));
    System.out.println(str1 + "=" + str2 + ":" + equals(str1, str2));

    str2 = "2";

    System.out.println(str1 + ">" + str2 + ":" + greateThan(str1, str2));
    System.out.println(str1 + "<" + str2 + ":" + lessThan(str1, str2));
    System.out.println(str1 + "=" + str2 + ":" + equals(str1, str2));
    str1 = "3";

    System.out.println(str1 + ">" + str2 + ":" + greateThan(str1, str2));
    System.out.println(str1 + "<" + str2 + ":" + lessThan(str1, str2));
    System.out.println(str1 + "=" + str2 + ":" + equals(str1, str2));

    System.out.println(count("where name=?? and sex=?? and n=?", "??"));
  }

  public static boolean isEmpty(String str)
  {
    return ((str == null) || (str.length() == 0));
  }
}
