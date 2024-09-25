import http from '@/utils/http'

// 1. 注册 API 测试
// 尝试使用提供的电子邮件地址注册用户
// 参数:
// - email: 用户的电子邮件地址
// 返回值: 注册尝试的响应结果
export async function testRegister(email) {
    try {
        const response = await http.post('/api/register', { email });
        console.log('注册响应:', response);
        return response;
    } catch (error) {
        alert('该邮箱已被注册')
        console.error('注册错误:', error);
        throw error;
    }
}

// 2. 获取用户名和密码 API 测试
// 尝试获取用户的用户名和密码
// 参数:
// - email: 用户的电子邮件地址
// - userName: 用户名
// - password: 密码
// 返回值: 获取用户名和密码操作的响应结果
export async function testAddUser(email, userName, password) {
    try {
        const response = await http.post('/api/add-user', { email, userName, password });
        console.log('获取用户响应:', response);
        return response;
    } catch (error) {
        alert('该用户名已被注册')
        console.error('获取用户错误:', error);
        throw error;
    }
}

// 3. 登录 API 测试
// 尝试使用用户名和密码登录
// 参数:
// - userName: 用户名
// - password: 密码
// 返回值: 登录操作的响应结果
export async function testLogin(userName, password) {
    try {
        const response = await http.post('/api/login', { userName, password });
        console.log('登录响应:', response);
        return response;
    } catch (error) {
        console.error('登录错误:', error);
        throw error;
    }
}

// 4. 修改密码 API 测试
// 尝试修改用户的密码
// 参数:
// - userName: 用户名
// - oldPassword: 当前密码
// - newPassword: 新密码
// 返回值: 修改密码操作的响应结果
export async function testChangePassword(userName, oldPassword, newPassword) {
    try {
        const response = await http.post('/api/change-password', { userName, oldPassword, newPassword });
        console.log('修改密码响应:', response);
        return response;
    } catch (error) {
        console.error('修改密码错误:', error);
        throw error;
    }
}

// 5. 生成问题 API 测试
// 尝试根据身份生成指定数量的问题
// 参数:
// - identity: 身份标识
// - questionCount: 需要生成的问题数量
// 返回值: 生成问题操作的响应结果
export async function testGenerateQuestions(identity, questionCount) {
    try {
        const response = await http.post('/api/generate-questions', { identity, questionCount });
        console.log('生成问题响应:', response);
        return response;
    } catch (error) {
        console.error('生成问题错误:', error);
        throw error;
    }
}

// 示例用法
async function runTests() {
    try {
        await testRegister('user@example.com');
        await testAddUser('user@example.com', 'admin', 'P@ssw0rd');
        await testLogin('admin', 'P@ssw0rd');
        await testChangePassword('admin', 'P@ssw0rd', 'NewP@ssw0rd');
        await testGenerateQuestions(1, 10);
    } catch (error) {
        console.error('测试套件错误:', error);
    }
}

// 运行所有测试
// runTests();

// 导出一个组件以满足 React 组件要求
export default function APITests() {
    return null;
}
