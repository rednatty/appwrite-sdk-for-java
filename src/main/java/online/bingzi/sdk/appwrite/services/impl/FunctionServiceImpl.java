package online.bingzi.sdk.appwrite.services.impl;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import online.bingzi.sdk.appwrite.Client;
import online.bingzi.sdk.appwrite.models.Deployment;
import online.bingzi.sdk.appwrite.models.Execution;
import online.bingzi.sdk.appwrite.models.Function;
import online.bingzi.sdk.appwrite.services.FunctionService;
import retrofit2.Call;

import java.util.List;
import java.util.Map;

/**
 * 函数服务实现类
 */
public class FunctionServiceImpl implements FunctionService {
    private final FunctionService functionService;

    public FunctionServiceImpl(Client client) {
        this.functionService = client.createService(FunctionService.class);
    }

    @Override
    public Call<Function> createFunction(String name, String runtime, List<String> execute,
                                       String id, List<String> events, String schedule,
                                       Integer timeout, Boolean enabled) {
        return functionService.createFunction(name, runtime, execute, id, events,
                schedule, timeout, enabled);
    }

    @Override
    public Call<List<Function>> listFunctions() {
        return functionService.listFunctions();
    }

    @Override
    public Call<Function> getFunction(String functionId) {
        return functionService.getFunction(functionId);
    }

    @Override
    public Call<Function> updateFunction(String functionId, String name, List<String> execute,
                                       List<String> events, String schedule,
                                       Integer timeout, Boolean enabled) {
        return functionService.updateFunction(functionId, name, execute, events,
                schedule, timeout, enabled);
    }

    @Override
    public Call<Void> deleteFunction(String functionId) {
        return functionService.deleteFunction(functionId);
    }

    @Override
    public Call<Deployment> createDeployment(String functionId, MultipartBody.Part code,
                                           Boolean activate, String entrypoint, String commands) {
        return functionService.createDeployment(functionId, code, activate, entrypoint, commands);
    }

    /**
     * 创建部署的便捷方法
     *
     * @param functionId 函数ID
     * @param code       代码内容
     * @param activate   是否激活
     * @param entrypoint 入口点
     * @param commands   构建命令
     * @return 部署信息
     */
    public Call<Deployment> createDeployment(String functionId, byte[] code,
                                           Boolean activate, String entrypoint, String commands) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), code);
        MultipartBody.Part codePart = MultipartBody.Part.createFormData("code", "code.zip", requestFile);
        return createDeployment(functionId, codePart, activate, entrypoint, commands);
    }

    @Override
    public Call<List<Deployment>> listDeployments(String functionId) {
        return functionService.listDeployments(functionId);
    }

    @Override
    public Call<Deployment> getDeployment(String functionId, String deploymentId) {
        return functionService.getDeployment(functionId, deploymentId);
    }

    @Override
    public Call<Void> deleteDeployment(String functionId, String deploymentId) {
        return functionService.deleteDeployment(functionId, deploymentId);
    }

    @Override
    public Call<Execution> createExecution(String functionId, Map<String, Object> data, Boolean async) {
        return functionService.createExecution(functionId, data, async);
    }

    @Override
    public Call<List<Execution>> listExecutions(String functionId) {
        return functionService.listExecutions(functionId);
    }

    @Override
    public Call<Execution> getExecution(String functionId, String executionId) {
        return functionService.getExecution(functionId, executionId);
    }
} 