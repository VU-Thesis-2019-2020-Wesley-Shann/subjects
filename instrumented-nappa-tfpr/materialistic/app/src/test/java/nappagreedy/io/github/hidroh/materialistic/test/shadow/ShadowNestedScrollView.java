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

import androidx.core.widget.NestedScrollView;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;
import org.robolectric.shadows.ShadowViewGroup;

import static org.robolectric.shadow.api.Shadow.directlyOn;

@Implements(NestedScrollView.class)
public class ShadowNestedScrollView extends ShadowViewGroup {
    @RealObject NestedScrollView realObject;
    private int smoothScrollY = -1;
    private int lastScrollDirection;

    @Implementation
    public  void smoothScrollTo(int x, int y) {
        smoothScrollY = y;
        directly().smoothScrollTo(x, y);
    }

    @Implementation
    public boolean pageScroll(int direction) {
        lastScrollDirection = direction;
        directly().pageScroll(direction);
        return true;
    }

    public int getSmoothScrollY() {
        return smoothScrollY;
    }

    public int getLastScrollDirection() {
        return lastScrollDirection;
    }

    private NestedScrollView directly() {
        return directlyOn(realObject, NestedScrollView.class);
    }
}
