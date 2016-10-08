# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/jay/work/bin/adt-bundle-mac-x86_64-20140702/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-optimizationpasses 5                                                           # 指定代码的压缩级别
#-dontusemixedcaseclassnames                                                     # 是否使用大小写混合
#-dontskipnonpubliclibraryclasses                                                # 是否混淆第三方jar
-dontpreverify                                                                  # 混淆时是否做预校验
-verbose                                                                        # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*        # 混淆时所采用的算法
-ignorewarnings

-keep public class * extends android.app.Activity                               # 保持哪些类不被混淆
-keep public class * extends android.app.Application                            # 保持哪些类不被混淆
-keep public class * extends android.app.Service                                # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver                  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider                    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper               # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference                      # 保持哪些类不被混淆
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.support.v4.app.FragmentActivity
-keep public class * extends android.os.FragmentActivity

-keep public class * extends com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
-keep public class * extends com.j256.ormlite.android.apptools.OpenHelperManager


-keepclasseswithmembernames class * {                                           # 保持 native 方法不被混淆
    native <methods>;
}

-keepclasseswithmembers class * {                                               # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);     # 保持自定义控件类不被混淆
}

-keepclassmembers class * extends android.app.Activity {                        # 保持自定义控件类不被混淆
   public void *(android.view.View);
}

-keepclassmembers enum * {                                                      # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {                                # 保持 Parcelable 不被混淆
  public static final android.os.Parcelable$Creator *;
}
# 保持 ButterKnife不被混淆
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}


# ACRA needs "annotations" so add this...
-keepattributes *Annotation*

-keepclasseswithmembers class * {
    void onClick*(...);
}
-keepclasseswithmembers class * {
    *** *Callback(...);
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# 保持枚举 enum 类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

-keep public class com.ylb.DoSimCard.R$*{
    public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


#-libraryjars libs/android-support-v4.jar
#-libraryjars libs/core.jar
#-libraryjars libs/android-support-v7-recyclerview.jar
#
##个推
#-libraryjars libs/GetuiExt-2.0.3.jar
#-libraryjars libs/GetuiSdk2.5.0.0.jar
#
#-libraryjars libs/eventbus-2.4.0.jar
#
#-libraryjars libs/ormlite-android-4.49.jar
#-libraryjars libs/ormlite-core-4.49.jar
#
#-libraryjars libs/ysidcard.jar
#-libraryjars libs/iDRBtLib.jar

#亿数
-keep public class com.ivsign.android.IDCReader.** {*;}
-keep public class com.otg.idcard.** {*;}

#精伦
-keep public class com.Routon.iDRBtLib.** {*;}

#神思
-keep public class com.identity.** {*;}
-keep public class com.ToBmp.** {*;}


-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.android.app.pay.IAlixPay{*;}
-keep class com.alipay.android.app.pay.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.pay.IAlixPayCallback{*;}
-keep class com.alipay.android.app.pay.IAlixPayCallback$Stub{*;}
-keep class com.alipay.android.app.script.**{*;}
-keep class com.alipay.android.app.pay.PayTask{*;}
-keep class com.alipay.android.app.pay.PayTask$OnPayListener{*;}
-keep class com.alipay.android.app.pay.CheckAccountTask{*;}
-keep class com.alipay.android.app.pay.CheckAccountTask$OnCheckListener{*;}
-keep class com.alipay.android.app.encrypt.**{*;}

-keep class com.alipay.mobile.command.*
-keep class android.webkit.*
-keep class com.alipay.mobilesecuritysdk.*
-keep class com.alipay.android.app.*
-keep class com.alipay.android.lib.*
-keep class com.alipay.android.mini.*
-keep class com.alipay.html.*
-keep class org.ccil.cowan.tagsoup.*
-keep class com.squareup.**
-dontwarn com.squareup.**

-keep class com.ut.*
-keep class com.alipay.test.ui.core.*
-keep class com.alipay.trobot.external.*
-keep class org.rome.android.ipp.*

-keep class com.shangdian.**
-dontwarn com.shangdian.**


-keep public class com.idcard.** {
}
-dontwarn com.idcard.**

-keep public class com.mining.app.zxing.** {
}
-dontwarn com.mining.app.zxing.**

-keep public class com.mining.app.zxing.** {
}
-dontwarn com.mining.app.zxing.**

-keep public class com.ylb.DoSimCard.ui.** {
}
-dontwarn com.ylb.DoSimCard.ui.**


-keepclassmembers class ** {
    public void onEvent*(**);
}

#ormlite
-keep public class com.j256.ormlite.** {
    public protected *;
}
-dontwarn com.j256.ormlite.**


#v4
-keep public class android.support.** {
    public protected *;
}
-dontwarn android.support.**

#getui
-keep public class com.igexin.** {
    public protected *;
}
-dontwarn com.igexin.**

#nineoldandroids
-keep public class com.nineoldandroids.** {
    public protected *;
}
-dontwarn com.nineoldandroid.**

#umpay
-keep public class com.umpay.** {
    public protected *;
}
-dontwarn com.umpay.**

#unionpay
-keep public class com.unionpay.** {
    public protected *;
}
-dontwarn com.unionpay.**

#unipay
-keep public class com.unipay.** {
}
-dontwarn com.unipay.**

#tencent
-keep public class com.tencent.mm.sdk.** {
}
-dontwarn com.tencent.mm.sdk.**

#tencent
-keep public class com.tenpay.** {
}
-dontwarn com.tenpay.**

#baidu
#tencent
-keep public class com.baidu.** {
}
-dontwarn com.baidu.**

-dontwarn com.github.mikephil.charting.**
-keep class com.github.mikephil.charting.** { *;}

#保证Model对象不被混淆，json反序列化的时候需要使用
-keep class com.ylb.DoSimCard.bean.**{*;}
-keep class com.ylb.info.**{*;}


##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

##---------------End: proguard configuration for Gson  ----------

##---------------微信----
-keep class com.umeng.** {*;}

-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn cn.sharesdk.**
-dontwarn **.R$*
-keep class m.framework.**{*;}
# LeakCanary
-keep class org.eclipse.mat.** { *; }
-keep class com.squareup.leakcanary.** { *; }
