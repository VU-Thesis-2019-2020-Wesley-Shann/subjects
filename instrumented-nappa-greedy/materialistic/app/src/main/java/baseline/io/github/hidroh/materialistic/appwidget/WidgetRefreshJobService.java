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

package baseline.io.github.hidroh.materialistic.appwidget;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class WidgetRefreshJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        new WidgetHelper(this).refresh(jobParameters.getJobId());
        jobFinished(jobParameters, false); // if we're able to start job means we have network conn
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
