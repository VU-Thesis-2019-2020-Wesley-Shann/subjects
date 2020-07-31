/*
 * Copyright (c) 2016 Ha Duy Trung
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

package nappagreedy.io.github.hidroh.materialistic.test.shadow;

import android.provider.SearchRecentSuggestions;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

import java.util.ArrayList;
import java.util.List;

@Implements(value = SearchRecentSuggestions.class)
public class ShadowSearchRecentSuggestions {
    public static int historyClearCount = 0;
    public static List<String> recentQueries = new ArrayList<>();

    @Implementation
    public void clearHistory() {
        historyClearCount++;
    }

    @Implementation
    public void saveRecentQuery(final String queryString, final String line2) {
        recentQueries.add(queryString);
    }
}
