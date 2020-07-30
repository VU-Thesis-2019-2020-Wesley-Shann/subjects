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

package nappagreedy.io.github.hidroh.materialistic;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import nappagreedy.io.github.hidroh.materialistic.data.ItemManager;
import nl.vu.cs.s2group.nappa.*;

public class NewActivity extends BaseStoriesActivity {
    public static final String EXTRA_REFRESH = NewActivity.class.getName() + ".EXTRA_REFRESH";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra(EXTRA_REFRESH, false)) {
            // triggered by new submission from user, refresh list
            ListFragment listFragment = ((ListFragment) getSupportFragmentManager()
                    .findFragmentByTag(LIST_FRAGMENT_TAG));
            if (listFragment != null) {
                listFragment.filter(getFetchMode());
            }
        }
    }

    @Override
    protected String getDefaultTitle() {
        return getString(R.string.title_activity_new);
    }

    @NonNull
    @Override
    protected String getFetchMode() {
        return ItemManager.NEW_FETCH_MODE;
    }

    @Override
    protected int getItemCacheMode() {
        return ItemManager.MODE_NETWORK;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));
    }
}