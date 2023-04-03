package com.mobilearts.nftworld.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.R
import com.mobilearts.nftworld.databinding.FragmentInAppBinding
import com.revenuecat.purchases.*

class InAppFragment : Fragment() {

    private lateinit var binding: FragmentInAppBinding
    private lateinit var pacList: List<Package>
    private var selectedOffering: Package? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pacList = mutableListOf()

        Purchases.sharedInstance.getOfferingsWith({
            // An error occurred
        }) { offerings ->
            pacList = offerings.current?.availablePackages.orEmpty()
            binding.monthlyTV.text = offerings.current?.monthly?.product?.price + "/Monthly"
            binding.annualTV.text = offerings.current?.annual?.product?.price + "/Annually"
            selectedOffering = offerings.current?.annual
        }

        binding.viewMonthly.setOnClickListener {
            selectPlan(binding.viewMonthly, binding.viewAnnually)
            binding.saveTV.visibility = View.INVISIBLE
            binding.viewMonthly.setBackgroundResource(R.drawable.btn_inappselected)
            binding.viewAnnually.setBackgroundResource(R.drawable.btn_inappunselected)
            selectedOffering = pacList.firstOrNull { it.packageType == PackageType.MONTHLY }
        }

        binding.viewAnnually.setOnClickListener {
            selectPlan(binding.viewAnnually, binding.viewMonthly)
            binding.saveTV.visibility = View.VISIBLE
            binding.viewAnnually.setBackgroundResource(R.drawable.btn_inappselected)
            binding.viewMonthly.setBackgroundResource(R.drawable.btn_inappunselected)
            selectedOffering = pacList.firstOrNull { it.packageType == PackageType.ANNUAL }
        }

        binding.ContinueTV.setOnClickListener {
            if (binding.viewMonthly.isSelected || binding.viewAnnually.isSelected) {
                selectedOffering?.let { offering ->
                    Purchases.sharedInstance.purchasePackageWith(requireActivity(), offering, { _, userCancelled ->
                        if (userCancelled) {
                            Toast.makeText(requireContext(), "You cancelled payment", Toast.LENGTH_SHORT).show()
                        }
                    }, { _, customerInfo ->
                        if (customerInfo.entitlements["pro"]?.isActive == true) {
                            Toast.makeText(requireContext(), "You are now pro", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
                findNavController().navigate(InAppFragmentDirections.actionInAppFragmentToHomeFragment())
            } else {
                Toast.makeText(requireContext(), "You have to choose one of the plans!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectPlan(selectedView: View, unselectedView: View) {
        selectedView.isSelected = true
        unselectedView.isSelected = false
    }
}