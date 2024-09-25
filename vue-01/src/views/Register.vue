<template>
  <div class="register-container">
    <el-card class="register-card">
      <template #header>
        <h2 class="register-title">创建新账户</h2>
      </template>
      <el-form :model="formData" :rules="rules" ref="registerForm">
        <el-form-item prop="email">
          <el-input v-model="formData.email" placeholder="电子邮箱">
            <template #prefix>
              <el-icon>
                <User/>
              </el-icon>
            </template>
          </el-input>
          <el-row :gutter="20" style="margin-top: 10px;">
            <el-col :span="16">
              <el-input v-model="formData.captcha" :placeholder="captchaPlaceholder" style="width: 100%;">
                <template #prefix>
                  <el-icon>
                    <Lock/>
                  </el-icon>
                </template>
              </el-input>
            </el-col>
            <el-col :span="8">
              <el-button type="primary" @click="handleCaptcha" style="width: 100%;">发送验证码</el-button>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSubmit" :loading="isLoading" class="register-button">
            {{ isLoading ? '注册中...' : '注册' }}
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import {ref, reactive} from 'vue'
import {ElMessage} from 'element-plus'
import {User, Lock} from '@element-plus/icons-vue'
import {testRegister} from "@/apis/api.js";
import {useUserInfo} from "@/stores/info.js";
import router from "@/router/index.js";

const userInfo = useUserInfo();

const registerForm = ref(null)
const isLoading = ref(false)

let retCaptcha = reactive('')
const formData = reactive({
  email: '',
  captcha: ''
})
const rules = {
  email: [
    {required: true, message: '请输入电子邮箱', trigger: 'blur'},
    {type: 'email', message: '请输入有效的电子邮箱地址', trigger: 'blur'}
  ],
  captcha: [
    {required: true, message: '请输入验证码', trigger: 'blur'},
    {min: 6, message: '验证码不能为空', trigger: 'blur'}
  ]
}

// 新增的状态变量
const captchaPlaceholder = ref('验证码');


const handleCaptcha = async () => {
  try {
    // 验证表单
    // await loginForm.value.validate();

    // 设置加载状态
    isLoading.value = true;

    console.log(formData)
    captchaPlaceholder.value = '请先不要输入验证码';

    // 调用登录接口
    const emailCheckResult = await testRegister(formData.email);

    // 判断登录结果
    if (emailCheckResult.status == '0') {
      console.error('注册失败: 邮箱已注册');
      ElMessage.error('注册失败: 邮箱已注册');
    } else {
      // 保存用户信息
      userInfo.email = formData.email;
      retCaptcha = emailCheckResult.registerCode

      console.log('邮箱验证成功!');
      ElMessage.success('邮箱验证成功');

      // 更改验证码输入框的提示文本
      captchaPlaceholder.value = '请现在输入验证码';

    }
  } catch (error) {
    console.error('邮箱验证过程中出现错误:', error);
    ElMessage.error('邮箱验证失败');
  } finally {
    // 无论成功还是失败，都需要重置加载状态
    isLoading.value = false;
  }
};

const handleSubmit = async () => {
  if(formData.captcha != retCaptcha){
    alert('验证码错误')
    return
  }
  userInfo.email = formData.email
  goToRegister()
}

const goToRegister = () => {
  router.push('/init-user')
}

</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.register-card {
  width: 100%;
  max-width: 400px;
}

.register-title {
  text-align: center;
  font-size: 24px;
  color: #303133;
}

.register-button {
  width: 100%;
}
</style>