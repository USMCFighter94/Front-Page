package com.brevitz.frontpage.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.brevitz.frontpage.R
import com.brevitz.frontpage.data.PostDataResponse.Error
import com.brevitz.frontpage.data.PostDataResponse.Success
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private val disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.main_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposables.addAll(viewModel.loadingObservable().subscribe {
            if (it) {
                // Show Loading...
            } else {
                // Hide Loading...
            }
        }, viewModel.dataObservable().subscribe {
            when (it) {
                is Success -> {
                }
                is Error -> AlertDialog.Builder(requireContext())
                    .setTitle("Error!")
                    .setMessage(it.throwable.localizedMessage)
                    .create().show()
            }
        })

        viewModel.getData("")
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
