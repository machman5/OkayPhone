-keep public class com.crearo.okayphone.commands.main.raw.* { public *; }
-keep public abstract class com.crearo.okayphone.commands.main.generals.* { public *; }
-keep public class com.crearo.okayphone.commands.tuixt.raw.* { public *; }

-keep public class com.crearo.okayphone.managers.notifications.NotificationService
-keep public class com.crearo.okayphone.managers.notifications.KeeperService

-keep public class com.crearo.okayphone.managers.options.**

-dontwarn com.crearo.okayphone.commands.main.raw.**

-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase