/*
 * Copyright 2019 LinkedIn Corporation
 * All Rights Reserved.
 *
 * Licensed under the BSD 2-Clause License (the "License").  See License in the project root for
 * license information.
 */
package com.linkedin.android.litr.render

import android.media.MediaFormat

internal class AudioResamplerFactory {

    fun createAudioResampler(sourceMediaFormat: MediaFormat?, targetMediaFormat: MediaFormat?): AudioResampler {
        return if (sourceMediaFormat != null &&
            targetMediaFormat != null &&
            sourceMediaFormat.containsKey(MediaFormat.KEY_SAMPLE_RATE) &&
            targetMediaFormat.containsKey(MediaFormat.KEY_SAMPLE_RATE) &&
            sourceMediaFormat.containsKey(MediaFormat.KEY_CHANNEL_COUNT) &&
            targetMediaFormat.containsKey(MediaFormat.KEY_CHANNEL_COUNT) &&
            (sourceMediaFormat.getInteger(MediaFormat.KEY_SAMPLE_RATE) != targetMediaFormat.getInteger(MediaFormat.KEY_SAMPLE_RATE)
                || sourceMediaFormat.getInteger(MediaFormat.KEY_CHANNEL_COUNT) != targetMediaFormat.getInteger(MediaFormat.KEY_CHANNEL_COUNT))) {
            OboeAudioResampler(
                sourceMediaFormat.getInteger(MediaFormat.KEY_CHANNEL_COUNT),
                sourceMediaFormat.getInteger(MediaFormat.KEY_SAMPLE_RATE),
                targetMediaFormat.getInteger(MediaFormat.KEY_CHANNEL_COUNT),
                targetMediaFormat.getInteger(MediaFormat.KEY_SAMPLE_RATE)
            )
        } else {
            PassthroughResampler()
        }
    }
}