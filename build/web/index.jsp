<!DOCTYPE html>
<html lang="en">

<head>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/index.css">
    <script src="script/log.js" defer></script>
    <script src="script/singup.js" defer></script>
    
</head>

<body>
    <div class="main-container">

        <div class="login-tab form-container">
            <form action="login">
                    <h1>Log in</h1><hr>
                    <div class="form-group">
                        <label for="email"><b>Email</b></label>
                        <input type="email" placeholder="Enter Email" name="email" required>
                    </div>

                    <div class="form-group">
                        <label for="password"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="password" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" >Login</button >
                    </div>
                    <div class="form-group">
                        <p>New user ?
                            <span class="sign-span" onclick="changeTabToSignUp()">Sign up</span>
                        </p>
                    </div>
            </form>
        </div>
        <div class="signup-tab form-container">
            <form>
                <h1>Sign up</h1>
                
                <p>Please fill in this form to create an account.</p>
                <hr>

                <div class="form-group">
                    <label for="name"><b>Enter name</b></label>
                    <input type="text" name="name" id="name" required>
                </div>

                <div class="form-group">
                    <label for="email"><b>Enter email</b></label>
                    <input type="email" name="email" id="email" required>
                </div>

                <div class="form-group">
                    <label for="password"><b> Enter Password</b></label>
                    <input type="password" name="password" id="password" required>
                </div>

                <div class="form-group gender-group">
                    <p><b>Select Gender</b></p>
                    <label for="male">Male</label>
                    <input type="radio" name="gender" id="male" value="male" required>
                    <label for="female">Female</label>
                    <input type="radio" name="gender" id="female" value="female" required>
                </div>

                <div class="form-group">
                    <input type="checkbox" name="terms" id="terms" required>
                    <label for="terms">Accept Terms and Condition</label>
                </div>

                <div class="form-group btn-group">
                    <button type="submit">
                        Sign Up
                    </button>
                </div>

                <div class="form-group">
                    <p>Already registered ?<span class="sign-span" onclick="changeTabToLogIn()">Log in</span></a></p>
                </div>
            </form>
        </div>
    </div>
</body>

</html>