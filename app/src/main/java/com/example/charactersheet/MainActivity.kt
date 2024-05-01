package com.example.charactersheet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.charactersheet.ui.theme.CharacterSheetTheme

private const val TAG = "com.example.charactersheet.MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharacterSheetTheme {
                CharacterSheetApp()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CharacterSheetTheme {
        CharacterSheetApp()
    }
}
