package org.example.util;

import org.slf4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestInvokedMethodListenerUtil implements IInvokedMethodListener {
    private static final Logger logger = LoggerUtil.getLogger(TestInvokedMethodListenerUtil.class);

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            logger.info("Запуск метода {}", method.getTestMethod().getMethodName());
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            if (testResult.isSuccess()) {
                logger.info("Метод {} завершился успешно", method.getTestMethod().getMethodName());
            } else {
                logger.error("Метод {} завершился c ошибкой: ", method.getTestMethod().getMethodName(), testResult.getThrowable());
            }
        }
    }
}
