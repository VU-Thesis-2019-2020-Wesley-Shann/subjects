/*
 * Copyright (c) 2015 Ha Duy Trung
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package baseline.io.github.hidroh.materialistic;

import android.util.Log;

import androidx.annotation.NonNull;

import baseline.io.github.hidroh.materialistic.data.ItemManager;

public class JobsActivity extends BaseStoriesActivity {
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("WESLEY_GRAPH_ACTIVITY", this.getClass().getSimpleName());
    }

    @Override
    protected String getDefaultTitle() {
        return getString(R.string.title_activity_jobs);
    }

    @NonNull
    @Override
    protected String getFetchMode() {
        return ItemManager.JOBS_FETCH_MODE;
    }

}
