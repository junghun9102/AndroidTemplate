package com.appge.androidtemplate

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileNotFoundException

/**
 *
 * InternalStorage path : /data/user/0/${packageName}/files
 *
 * 2번을 사용하면 루트 디렉토리에만 접근이 가능해보인다.
 * 임시 파일을 저장 후 서버에 전송한다면 이러한 접근이 간편해보인다.
 *
 * 하지만 InternalStorage 를 적극 사용하고 싶다면
 * 폴더를 만들어 1번과 같이 사용하는게 좋아보인다.
 *
 */

class MainActivity : AppCompatActivity() {

    private val internalStoragePath: String by lazy { filesDir.path }
    private val saveFileDirectory = "/textFolder"

    private val saveStoragePath: String by lazy { "$internalStoragePath$saveFileDirectory" }
    private val fileName = "textFileName.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initData()
        makeDirectory()
    }

    private fun initViews() {
        btn_main_read.setOnClickListener { loadTextFromFile() }
        btn_main_write.setOnClickListener { saveTextToFile() }
        btn_main_clear.setOnClickListener { clear() }
        et_main_note.requestFocus()
        updateFileInform()
        updateInternalStoragePath()
    }

    private fun initData() {
        loadTextFromFile()
    }

    private fun updateInternalStoragePath() {
        tv_main_internal_storage_path.text = filesDir.path
    }

    private fun makeDirectory() {
        val directory = File(saveStoragePath)
        if (directory.exists().not()) {
            directory.mkdir()
        }
    }

    private fun loadTextFromFile() {
        try {
            // 1
            val savedNoteFile = File(saveStoragePath, fileName)
            val note = FileManager.getTextFromFile(savedNoteFile)
            showNote(note)

//            // 2
//            val inputStream = openFileInput(fileName)
//            val note = FileManager.getTextFromInputStream(inputStream)
//            inputStream.close()
//            showNote(note)

            Toast.makeText(this, "저장된 파일을 읽어 왔습니다.", Toast.LENGTH_SHORT).show()
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "저장된 파일이 없습니다.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "알 수 없는 에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveTextToFile() {
        try {
            // 1
            val file = File(saveStoragePath, fileName)
            val textToSave = et_main_note.text.toString()
            FileManager.writeTextToFile(file, textToSave)

//            // 2
//            val outputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
//            val textToSave = et_main_note.text.toString()
//            FileManager.writeTextToOutputStream(outputStream, textToSave)
//            outputStream.close()

            Toast.makeText(this, "파일을 저장했습니다.", Toast.LENGTH_SHORT).show()
            updateFileInform()
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "저장할 파일을 찾을 수 없습니다..", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("Test", "${e.message}")
            Toast.makeText(this, "알 수 없는 에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clear() {
        et_main_note.text.clear()
        deleteSavedFile()
    }

    private fun deleteSavedFile() {
        try {
            val file = File(saveStoragePath, fileName)

            if (file.exists()) {
                file.delete()
                Toast.makeText(this, "파일을 삭제했습니다.", Toast.LENGTH_SHORT).show()
                updateFileInform()
            } else {
                Toast.makeText(this, "삭제할 파일이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "알 수 없는 에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showNote(content: String) {
        et_main_note.setText(content)
        et_main_note.setSelection(et_main_note.text.length)
    }

    private fun updateFileInform() {
        val fileInform = "InternalStorage has ${getInternalStorageInform()}\n" +
                "SaveStorage has ${getSaveStorageInform()}"
        tv_main_save_storage_path.text = fileInform
    }

    private fun getInternalStorageInform() = getInformFromStringList(fileList())

    private fun getSaveStorageInform(): String {
        val file = File(saveStoragePath)
        return getInformFromStringList(file.list())
    }

    private fun getInformFromStringList(stringList: Array<String>?): String {
        return if (stringList.isNullOrEmpty()) {
            "nothing"
        } else {
            stringList.fold("") { sum, new ->
                sum + if (sum.isNotBlank()) ", $new" else new
            }
        }
    }

}
