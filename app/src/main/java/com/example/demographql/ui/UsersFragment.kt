package com.example.demographql.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demographql.DemoGraphQLApplication
import com.example.demographql.MainActivity
import com.example.demographql.R
import com.example.demographql.Status
import com.example.demographql.data.UserTransformer
import com.example.demographql.data.UserViewModel
import com.example.demographql.databinding.FragmentUsersBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    @Inject lateinit var userViewModel: UserViewModel
    @Inject lateinit var userTransformer: UserTransformer
    private lateinit var recyclerView: RecyclerView
    private lateinit var context: Context
    private lateinit var userListAdapter: UserListAdapter

    override fun onAttach(context: Context) {
        ((activity as MainActivity).application as DemoGraphQLApplication)
            .applicationComponent.inject(this);
        this.context = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        binding.usersListItemHeader.root.background = R.color.purple_200.toDrawable()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch() {
            userViewModel.getUserFeature().getUsersLiveData()
        }

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        recyclerView = binding.fragmentUsersRecycleView
        recyclerView.layoutManager = LinearLayoutManager(context)

        userListAdapter = UserListAdapter()
        userListAdapter.setUserList(ArrayList())
        recyclerView.adapter = userListAdapter

        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun setupObservers() {
        userViewModel.getUserFeature().usersLiveData.observe(viewLifecycleOwner) { res ->
            when (res.status) {
                Status.LOADING -> {
                    setLoaderTextView(getText(R.string.loading_state))
                }
                Status.SUCCESS -> {
                    setLoaderTextView(getText(R.string.success_state))
                    if (res.value == null || res.value!!.data == null) {
                        res.status = Status.ERROR
                        return@observe
                    }
                    res.value?.data?.let {
                        userTransformer.transformToUserList(it.getAllUsers)
                    }?.let {
                        userListAdapter.setUserList(it)
                    }
                }
                else -> {
                    setLoaderTextView(getText(R.string.error_state))
                }
            }
        }
    }

    private fun setLoaderTextView(text: CharSequence) {
        binding.loaderTextview.text = text
    }
}
