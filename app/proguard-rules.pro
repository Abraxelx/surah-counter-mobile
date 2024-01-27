# Android Studio'nun ön tanımlı kuralları:
-keep class com.google.** { *; }
-keep class androidx.** { *; }
-keep class androidx.lifecycle.** { *; }
-keep class androidx.room.** { *; }

-keep class com.abraxel.quran_counter.** { *; }

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# Gson kütüphanesi ile kullanılan kurallar:
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.stream.** { *; }

# ButterKnife kütüphanesi ile kullanılan kurallar:
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

