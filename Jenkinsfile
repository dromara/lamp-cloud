#!groovy
pipeline {
    agent any

    environment {
        // jenkins 上动态配置的参数：
        // SERVER_SSH_HOST: 推送到那台服务器
//       MAVEN_COMMAND：  在jenkins服务器编译代码 (package install deploy表示maven的编译命令，none 表示不编译源码，使用上次编译的jar)
//       IS_PUSH_JAR： 是否将jenkins服务器的编译后的jar，推送到部署服务器
//       ACTION：   在部署服务器执行(停止、启动、重启等)动作  （none表示不执行动作）
//       SERVER_NAME： "lamp-authority"

        // 根据项目或部署服务器 可能需要更改一次的变量

        // 以下变量基本不变
        // 推送到服务器端的文件夹路径
        remoteDirectory = "./temp_jar/"

        JAR_NAME = "${SERVER_NAME}-server"
        // 推送时需要忽略的项目前缀
        removePrefix = "${SERVER_NAME}/${JAR_NAME}/target/"

        // 需要推送到服务器端的文件(jar)
        sourceFiles = "${SERVER_NAME}/${JAR_NAME}/target/${JAR_NAME}.jar"
    }

    stages {
        stage('替换环境参数') {
            steps {
                script {
                    // SERVER_SSH_HOST一定要包含_ 如：ip_dev ip_prod 等格式
                    PROFILES_ARR = "${SERVER_SSH_HOST}".split('_')
                    PROFILES = PROFILES_ARR[1]

                    // 工作空间
                    WORKSPACE_HOME = "/data_${PROFILES}"

                    // 服务端执行的脚本
                    EXEC_COMMAND = "bash -x -s < ${WORKSPACE_HOME}/bin/server_run.sh ${JAR_NAME} ${PROFILES} ${ACTION}"
                    echo "您选择了如下参数："
                    echo "拉取分支： ${branch}, 打包命令：${MAVEN_COMMAND}，是否推送jar：${IS_PUSH_JAR}, 将${SERVER_NAME} 推送至：${SERVER_SSH_HOST}, 运行环境参数：${PROFILES}，启动动作：${ACTION}"
                }
            }
        }

        stage('maven编译代码') {
            steps {
                script {
                    if ("${MAVEN_COMMAND}" != "none") {
                        sh "mvn clean ${MAVEN_COMMAND} -T2 -Dmaven.compile.fork=true -Dmaven.test.skip=true -P ${PROFILES}"
                    } else {
                        echo "无需编译项目 (适用于代码没有改动的场景)"
                    }
                }
            }
        }

        stage('推送jar&执行动作') {
            steps {
                script {
                    if ("${IS_PUSH_JAR}" == "false") {
                        echo "无需推送jar到服务器：【${IS_PUSH_JAR}】"
                        sourceFiles = ""
                    }

                    if ("${ACTION}" == "none") {
                        echo "无需执行重启等动作"
                    }

                    echo "${EXEC_COMMAND}"
                    sshPublisher(publishers: [sshPublisherDesc(configName: "${SERVER_SSH_HOST}", transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: "${EXEC_COMMAND}", execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: "${remoteDirectory}", remoteDirectorySDF: false, removePrefix: "${removePrefix}", sourceFiles: "${sourceFiles}")], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
                }

            }
        }
    }

}


