package com.example.springbatchexample.batchprocessing;

import org.springframework.batch.core.ExitStatus;
import org.springframework.stereotype.Component;

@Component
public class ExampleService {

    /**
     * 	private int severity(ExitStatus status) {
     * 		if (status.exitCode.startsWith(EXECUTING.exitCode)) {
     * 			return 1;
     *                }
     * 		if (status.exitCode.startsWith(COMPLETED.exitCode)) {
     * 			return 2;
     *        }
     * 		if (status.exitCode.startsWith(NOOP.exitCode)) {
     * 			return 3;
     *        }
     * 		if (status.exitCode.startsWith(STOPPED.exitCode)) {
     * 			return 4;
     *        }
     * 		if (status.exitCode.startsWith(FAILED.exitCode)) {
     * 			return 5;
     *        }
     * 		if (status.exitCode.startsWith(UNKNOWN.exitCode)) {
     * 			return 6;
     *        }
     * 		return 7;*
     * 		}
     * @return
     */
    public ExitStatus doing() {
        System.out.println("=======================================");
        System.out.println("ExampleService:Step=====> ExampleService");
        System.out.println("=======================================");
        ExitStatus result = ExitStatus.COMPLETED;//後続へ続く    .変換あとの終了コードは０らしい。
//        ExitStatus result = ExitStatus.FAILED;//後続へ続く    .変換あとの終了コードは１らしい。
//        ExitStatus result = ExitStatus.STOPPED;//後続へ続かない。このStepで終了
        return result;
    }

}
