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

package onlymash.flexbooru.repository.tag

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import onlymash.flexbooru.api.DanbooruApi
import onlymash.flexbooru.entity.tag.TagDan
import onlymash.flexbooru.entity.tag.SearchTag
import java.util.concurrent.Executor

/**
 * Danbooru tags data source factory which also provides a way to observe the last created data source.
 * This allows us to channel its network request status etc back to the UI. See the Listing creation
 * in the Repository class.
 */
class TagDanDataSourceFactory(
    private val danbooruApi: DanbooruApi,
    private val search: SearchTag,
    private val retryExecutor: Executor) : DataSource.Factory<Int, TagDan>() {
    //source livedata
    val sourceLiveData = MutableLiveData<TagDanDataSource>()
    override fun create(): DataSource<Int, TagDan> {
        val source = TagDanDataSource(danbooruApi, search, retryExecutor)
        sourceLiveData.postValue(source)
        return source
    }
}