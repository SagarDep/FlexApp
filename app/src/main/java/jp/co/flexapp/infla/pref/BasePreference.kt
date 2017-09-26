package jp.co.flexapp.infla.pref

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by mitsuhori_y on 2017/09/26.
 */
internal class BasePreferences(context: Context) {

    /**
     * SharedPreferences
     */
    val sharedPreferences: SharedPreferences

    init {
        this.sharedPreferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
    }

    /**
     * Editorを取得.

     * @return Editor
     */
    val editor: SharedPreferences.Editor
        get() = sharedPreferences.edit()

    /**
     * 内容をSharedPreferencesに保存.

     * @param key   キー
     * *
     * @param value バリュー
     */
    fun putString(key: String, value: String) {
        val editor = editor
        editor.putString(key, value)
        editor.commit()
    }

    /**
     * 内容をSharedPreferencesに保存.

     * @param key   キー
     * *
     * @param value バリュー
     */
    fun putBoolean(key: String, value: Boolean) {
        val editor = editor
        editor.putBoolean(key, value)
        editor.commit()
    }

    /**
     * 内容をSharedPreferencesに保存.

     * @param key   キー
     * *
     * @param value バリュー
     */
    fun putInt(key: String, value: Int) {
        val editor = editor
        editor.putInt(key, value)
        editor.commit()
    }

    /**
     * 指定されたキーの情報を削除する

     * @param key キー
     */
    fun remove(key: String) {
        val editor = editor
        editor.remove(key)
        editor.commit()
    }

    fun getString(key: String, replaceValue: String): String {
        return sharedPreferences.getString(key, replaceValue)
    }

    val preferencesName: String
        get() = SP_NAME

    companion object {

        private val SP_NAME = "Preferences"
    }
}
