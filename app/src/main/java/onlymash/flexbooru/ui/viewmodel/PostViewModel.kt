/*
 * Copyright (C) 2019 by onlymash <im@fiepi.me>, All rights reserved
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package onlymash.flexbooru.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import onlymash.flexbooru.entity.Search
import onlymash.flexbooru.repository.post.PostRepository

class PostViewModel(private val repo: PostRepository): ViewModel() {

    private val searchData = MutableLiveData<Search>()
    private val danOneRepoResult = map(searchData) { search ->
        repo.getDanOnePosts(search)
    }
    val postsDanOne = switchMap(danOneRepoResult) { it.pagedList }
    val networkStateDanOne = switchMap(danOneRepoResult) { it.networkState }
    val refreshStateDanOne = switchMap(danOneRepoResult) { it.refreshState }
    private val danRepoResult = map(searchData) { search ->
        repo.getDanPosts(search)
    }
    val postsDan = switchMap(danRepoResult) { it.pagedList }
    val networkStateDan = switchMap(danRepoResult) { it.networkState }
    val refreshStateDan = switchMap(danRepoResult) { it.refreshState }
    private val moeRepoResult = map(searchData) { search ->
        repo.getMoePosts(search)
    }
    val postsMoe = switchMap(moeRepoResult) { it.pagedList }
    val networkStateMoe = switchMap(moeRepoResult) { it.networkState }
    val refreshStateMoe = switchMap(moeRepoResult) { it.refreshState }
    private val gelRepoResult = map(searchData) { search ->
        repo.getGelPosts(search)
    }
    val postsGel = switchMap(gelRepoResult) { it.pagedList }
    val networkStateGel = switchMap(gelRepoResult) { it.networkState }
    val refreshStateGel = switchMap(gelRepoResult) { it.refreshState }
    fun show(search: Search): Boolean {
        if (searchData.value == search) {
            return false
        }
        searchData.value = search
        return true
    }
    fun refreshDanOne() {
        danOneRepoResult.value?.refresh?.invoke()
    }
    fun refreshDan() {
        danRepoResult.value?.refresh?.invoke()
    }
    fun refreshMoe() {
        moeRepoResult.value?.refresh?.invoke()
    }
    fun retryDanOne() {
        danOneRepoResult.value?.retry?.invoke()
    }
    fun retryDan() {
        danRepoResult.value?.retry?.invoke()
    }
    fun retryMoe() {
        moeRepoResult.value?.retry?.invoke()
    }
    fun refreshGel() {
        gelRepoResult.value?.refresh?.invoke()
    }
    fun retryGel() {
        gelRepoResult.value?.retry?.invoke()
    }
}