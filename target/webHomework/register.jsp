<%--
  Created by IntelliJ IDEA.
  User: 徐鹏丰
  Date: 2023/7/24
  Time: 15:25
  功能：注册登录界面
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>账号注册</title>
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
<video id="background-video" autoplay muted loop>
    <source src="mp4/video%20(720p).mp4">
</video>
<form id="login-form" action="userRegister" method="post">
    <div class="content" >
        <div class="register-content">
            <a href="" class="banner-box"></a>
            <div class="register-box">
                <h1>账号注册</h1>
                <input type="text" name="username" placeholder="账号" id="username" onfocus="clearPasswordError()">
                <input type="password" name="password" id="password" placeholder="密码" oninput="checkPassword()"
                       onfocus="clearPasswordError()">
                <input type="password" id="confirm-password" placeholder="确认密码" oninput="checkConfirmPassword()">
                <span id="password-error" class="error-message"></span>
                <span id="confirm-password-error" class="error-message"></span>

                <!-- 添加验证码文字的容器 -->
                <div id="captchaContainer">
                    <canvas id="captchaCanvas" width="120" height="40" onclick="refreshCaptcha()"></canvas>
                    <a id="refreshCaptcha" href="javascript:void(0);" onclick="refreshCaptcha()">看不清？换一张</a>
                </div>
                <input type="text" id="captcha" required placeholder="请输入验证码">

                <!-- 服务协议勾选 -->
                <label>
                    <input type="checkbox" id="agree-checkbox">
                    我已阅读并同意<a href="agreement.html" target="_self" class="agreement-body">服务协议</a>
                </label>

<%--                <input class="register-btn" onclick="validateForm()" type="submit">注册</input>--%>
                <input type="submit" value="注册"  class="register-btn">
            </div>
        </div>
    </div>
</form>
<script>
    function validatePasswords() {
        // 数据验证和注册逻辑保持不变
        // ...
    }

    // 实时检查密码是否符合正则表达式规则
    function checkPassword() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirm-password").value;
        var passwordPattern = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{8,16}$/;

        // 使用正则表达式验证密码规则
        if (passwordPattern.test(password)) {
            document.getElementById("password").style.color = "green";
            document.getElementById("password-error").innerText = "";
        } else {
            document.getElementById("password").style.color = "red";
            document.getElementById("password-error").innerText = "密码必须包含至少一个数字、至少一个小写字母和至少一个大写字母，并且8-16个字符长度！";
        }

        // 检查确认密码是否与密码一致
        if (confirmPassword !== "" && password !== confirmPassword) {
            document.getElementById("confirm-password").style.color = "red";
            document.getElementById("confirm-password-error").innerText = "确认密码与密码不一致！";
        } else {
            document.getElementById("confirm-password").style.color = "green";
            document.getElementById("confirm-password-error").innerText = "";
        }
    }

    // 实时检查确认密码是否与密码一致
    function checkConfirmPassword() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirm-password").value;

        // 清空确认密码错误信息
        document.getElementById("confirm-password-error").innerText = "";

        // 检查两次密码是否相同
        if (confirmPassword !== "" && password !== confirmPassword) {
            document.getElementById("confirm-password").style.color = "red";
            document.getElementById("confirm-password-error").innerText = "确认密码与密码不一致！";
        } else {
            document.getElementById("confirm-password").style.color = "green";
            document.getElementById("confirm-password-error").innerText = "";
        }
    }

    // 清除密码错误信息
    function clearPasswordError() {
        document.getElementById("password").style.color = "";
        document.getElementById("password-error").innerText = "";
        document.getElementById("confirm-password-error").innerText = "";
    }

    // 生成图形验证码
    function generateCaptcha() {
        var canvas = document.getElementById("captchaCanvas");
        var context = canvas.getContext("2d");

        // 清空画布
        context.clearRect(0, 0, canvas.width, canvas.height);

        // 生成随机验证码
        var captchaText = generateRandomText(6);

        // 将验证码文本显示在 canvas 上
        context.font = "20px Arial";
        context.fillStyle = "black";
        context.textAlign = "center";
        context.textBaseline = "middle";
        context.fillText(captchaText, canvas.width / 2, canvas.height / 2);

        // 绘制波浪线
        var waveLineWidth = 2;
        context.strokeStyle = "black";
        context.lineWidth = waveLineWidth;
        var amplitude = 5; // 波浪振幅
        var frequency = 0.1; // 波浪频率

        for (var x = 0; x < canvas.width; x += waveLineWidth) {
            var y = amplitude * Math.sin(x * frequency);
            context.beginPath();
            context.moveTo(x, canvas.height / 2 + y);
            context.lineTo(x + waveLineWidth, canvas.height / 2 + y);
            context.stroke();
        }

        // 存储验证码文本，用于与用户输入进行对比
        document.getElementById("captcha").dataset.captchaText = captchaText.toLowerCase();
        document.getElementById("captchaText").textContent = captchaText.toLowerCase();
    }

    // 生成指定长度的随机文本
    function generateRandomText(length) {
        var characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        var randomText = "";
        for (var i = 0; i < length; i++) {
            var randomIndex = Math.floor(Math.random() * characters.length);
            randomText += characters.charAt(randomIndex);
        }
        return randomText;
    }

    // 刷新验证码
    function refreshCaptcha() {
        generateCaptcha();
    }

    // 页面加载完成后生成初始验证码
    window.onload = function() {
        generateCaptcha();
    };

    // 验证表单提交
    function validateForm() {
        var captchaText = document.getElementById("captcha").dataset.captchaText;
        var userInputCaptcha = document.getElementById("captcha").value;
        var agreeCheckbox = document.getElementById("agree-checkbox").checked;

        // 验证密码和确认密码
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirm-password").value;
        var passwordPattern = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{8,16}$/;

        // 使用正则表达式验证密码规则
        if (!passwordPattern.test(password)) {
            document.getElementById("password").style.color = "red";
            document.getElementById("password-error").innerText = "密码必须包含至少一个数字、至少一个小写字母和至少一个大写字母，并且8-16个字符长度！";
            return false; // 阻止表单提交
        }

        // 检查确认密码是否与密码一致
        if (confirmPassword !== password) {
            document.getElementById("confirm-password").style.color = "red";
            document.getElementById("confirm-password-error").innerText = "确认密码与密码不一致！";
            return false; // 阻止表单提交
        }


        // 验证验证码
        if (userInputCaptcha !== captchaText) {
            document.getElementById("captcha").style.color = "red";
            return false; // 阻止表单提交
        } else {
            document.getElementById("captcha").style.color = "green";
        }

        // 验证是否勾选服务协议
        if (!agreeCheckbox) {
            alert("请同意服务协议！");
            return false; // 阻止表单提交
        }

        document.getElementById("login-form").submit();
    }

    // 绑定表单提交事件
    document.getElementById("login-form").addEventListener("submit", function(event) {
        event.preventDefault(); // 阻止表单提交
        validateForm(); // 执行表单验证逻辑
    });

    // 绑定实时检查验证码事件
    document.getElementById("captcha").addEventListener("input", function () {
        checkCaptcha();
    });

    // 实时检查验证码
    function checkCaptcha() {
        var captchaText = document.getElementById("captcha").dataset.captchaText;
        var userInputCaptcha = document.getElementById("captcha").value;

        console.log(captchaText)
        console.log(userInputCaptcha)

        if (userInputCaptcha === captchaText) {
            document.getElementById("captcha").style.color = "green";
        } else {
            document.getElementById("captcha").style.color = "red";
        }
    }
</script>
<script>
    var loginMessage = "${loginMessage}";
    if (loginMessage !== '') {
        alert(loginMessage);
    }
</script>
</body>
</html>
