/*
 * Copyright (c) 2014,KJFrameForAndroid Open Source Project,张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hihiwjc.libs.common_libs.utils;

import android.util.Log;

/**
 * 应用程序的Log管理<br>
 * <p>Created by hihiwjc on 2015/9/14 0014.</p>
 * <p>Author:hihiwjc</p>
 * <p>Email:hihiwjc@live.com</p>
 */
public final class Logger {
    public static boolean IS_DEBUG = true;
    public static boolean DEBUG_LOG = true;
    public static boolean SHOW_ACTIVITY_STATE = true;
    public static String TAG = "TAG";

    public static final void openDebutLog(boolean enable) {
        IS_DEBUG = enable;
        DEBUG_LOG = enable;
    }

    public static final void openActivityState(boolean enable) {
        SHOW_ACTIVITY_STATE = enable;
    }

    public static final void debug(String msg) {
        if (IS_DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static final void log(String packName, String state) {
        debugLog(packName, state);
    }

    public static final void debug(String msg, Throwable tr) {
        if (IS_DEBUG) {
            Log.i(TAG, msg, tr);
        }
    }

    public static final void state(String packName, String state) {
        if (SHOW_ACTIVITY_STATE) {
            Log.d(TAG, packName + state);
        }
    }

    public static final void debugLog(String packName, String state) {
        if (DEBUG_LOG) {
            Log.d(TAG, packName + state);
        }
    }

    public static final void exception(Exception e) {
        if (DEBUG_LOG) {
            e.printStackTrace();
        }
    }

    public static final void debug(String msg, Object... format) {
        debug(String.format(msg, format));
    }
}
