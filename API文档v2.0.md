### 1. **注册接口**

- **接口名称**: `POST /api/register`
- **描述**: 用户提交邮箱，系统发送注册码至用户邮箱，后端判断邮箱未使用。

#### 请求参数:

- **Content-Type**: `application/x-www-form-urlencoded`
- **请求体**:
  ```
  email=user@example.com
  ```

#### 成功响应:

```json
{
  "registerCode": "123456",
  "message": "Registration code sent successfully.",
  "status": "1"
}
```

#### 错误响应:

```json
{
  "message": "Email is already registered.",
  "status": "0"
}
```

---

### 2. **设置用户名和密码接口**

- **接口名称**: `POST /api/add-user`
- **描述**: 用户在完成注册后，设置用户名和密码，并验证用户名是否未被占用。

#### 请求参数:

- **Content-Type**: `application/x-www-form-urlencoded`
- **请求体**:
  ```
  email=user@example.com&userName=admin&password=P%40ssw0rd
  ```

#### 成功响应:

```json
{
  "message": "Password set successfully.",
  "status": "1"
}
```

#### 错误响应:

```json
{
  "message": "userName is already registered.",
  "status": "0"
}
```

---

### 3. **登录接口**

- **接口名称**: `POST /api/login`
- **描述**: 用户通过用户名和密码进行登录。

#### 请求参数:

- **Content-Type**: `application/x-www-form-urlencoded`
- **请求体**:
  ```
  userName=admin&password=P%40ssw0rd
  ```

#### 成功响应:

```json
{
  "message": "Login successful.",
  "status": "1"
}
```

#### 错误响应:

```json
{
  "message": "Invalid userName or password.",
  "status": "0"
}
```

---

### 4. **修改密码接口**

- **接口名称**: `POST /api/change-password`
- **描述**: 用户通过旧密码验证后修改为新密码。

#### 请求参数:

- **Content-Type**: `application/x-www-form-urlencoded`
- **请求体**:
  ```
  userName=admin&oldPassword=OldP%40ssw0rd&newPassword=NewP%40ssw0rd
  ```

#### 成功响应:

```json
{
  "message": "Password changed successfully.",
  "status": "1"
}
```

#### 错误响应:

```json
{
  "message": "Old password is incorrect.",
  "status": "0"
}
```

---

### 5. **生成试题接口**

- **接口名称**: `POST /api/generate-questions`
- **描述**: 根据用户的学段选择和题目数量生成对应的试题。
- 小学:1 初中:2 高中:3
- answer 为正确选项编号

#### 请求参数:

- **Content-Type**: `application/x-www-form-urlencoded`
- **请求体**:
  ```
  identity=1&questionCount=10
  ```

#### 成功响应:

```json
{
  "questions": [
    {
      "question": "2 + 2 = ",
      "options": ["2", "3", "4", "5"],
      "answer": "2"
    },
    {
      "question": "5 * 6 = ",
      "options": ["30", "31", "32", "33"],
      "answer": "0"
    }
  ]
}
```

---

以上所有接口的请求参数均被转换为 `x-www-form-urlencoded` 编码形式，并且请求体中的特殊字符会根据 URL 编码规则进行转义，如 `@` 转换为 `%40`。
