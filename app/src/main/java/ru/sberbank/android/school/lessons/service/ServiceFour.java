package ru.sberbank.android.school.lessons.service;

import android.animation.ValueAnimator;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import ru.sberbank.android.school.lessons.util.DataProviderUtil;

public class ServiceFour extends Service {

    public static final Intent newIntent(Context context) {
        return new Intent(context, ServiceFour.class);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    public class LocalBinder extends Binder {
        public ServiceFour getService() {
            return ServiceFour.this;
        }
    }

    public String[] getRandomWords(int count) {
        return DataProviderUtil.getRandomWords(this, count);
    }

    public ValueAnimator animateButton(final Button button, long duration) {
        ValueAnimator animator = ValueAnimator.ofInt(0, 359);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) button.getLayoutParams();
                layoutParams.circleAngle = (Integer) valueAnimator.getAnimatedValue();
                button.setLayoutParams(layoutParams);
            }
        });

        animator.setDuration(duration);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);

        return animator;
    }
}
