package com.example.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.file.Files.size








class MainActivity : AppCompatActivity() {
    private val mWordList = LinkedList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..19) {
            mWordList.addLast("Word $i")
        }
        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview)
// Create an adapter and supply the data to be displayed.
        mAdapter = WordListAdapter(this, mWordList)
// Connect the adapter with the RecyclerView.
        mRecyclerView!!.adapter = mAdapter
// Give the RecyclerView a default layout manager.
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.reset -> {
                mWordList.clear()
                for (i in 0..19) {
                    mWordList.addLast("Word $i")
                }
                (mRecyclerView?.adapter as WordListAdapter).mWordList = mWordList
                recyclerview.adapter?.notifyDataSetChanged()


            }
        }
        return true
    }

    fun onClick(view: View) {
        val wordListSize = mWordList.size
        // Add a new word to the wordList.
        mWordList.addLast("+ Word $wordListSize")
        // Notify the adapter, that the data has changed.
        mRecyclerView?.getAdapter()?.notifyItemInserted(wordListSize)
        // Scroll to the bottom.
        mRecyclerView?.smoothScrollToPosition(wordListSize)
    }

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: WordListAdapter? = null
}
