import "./LoginSignup.css";
import email_icon from "../Assets/email1.png";
import password_icon from "../Assets/password1.png";
import user_icon from "../Assets/user1.png";
import { useState } from "react";
import view_icon from "../Assets/view.png";
import hide_icon from "../Assets/hide.png";
import axios from "axios";

const LoginSignup = () => {
  const [isPassVisible, setIsPassVisible] = useState(true);
  const [action, setAction] = useState("Sign Up");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [buttonAction, setButtonAction] = useState({
    signup: true,
    loginup: true,
    submitup: false,
  });

  const handleToggle = (x) => {
    setButtonAction({
      signup: x === "Login",
      loginup: x === "Sign Up",
      submitup: true,
    });
    setAction(x);
  };

  const changeVisibility = () => {
    setIsPassVisible(!isPassVisible);
  };

  const handleSubmit = async () => {
    if (action === "Sign Up") {
      try {
        const response = await axios.post("http://localhost:9193/user/register", {
          name,
          username: email,
          password,
          email,
        });
        console.log("User registered:", response.data);
        // Handle post-registration logic here
      } catch (error) {
        console.error("Error registering user:", error);
      }
    } else {
      try {
        const response = await axios.post("http://localhost:9193/user/login", {
          username: email,
          password,
        });
        console.log("User logged in:", response.data);
        // Handle login logic here (e.g., store JWT)
      } catch (error) {
        console.error("Error logging in:", error);
      }
    }
  };

  return (
    <div className="container">
      <div className="header">
        <div className="text">{action}</div>
        <div className="underline"></div>
      </div>
      <div className="inputs">
        {action === "Login" ? null : (
          <div className="input">
            <img
              src={user_icon}
              alt="user"
              style={{ width: "27px", height: "25px", marginLeft: "30px" }}
            />
            <input
              type="text"
              placeholder="Name"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>
        )}

        <div className="input">
          <img
            src={email_icon}
            alt="email"
            style={{ width: "35px", height: "35px" }}
          />
          <input
            type="email"
            placeholder="Email Id"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        <div className="input">
          <img
            src={password_icon}
            alt="password"
            style={{ width: "35px", height: "35px" }}
          />
          <input
            type={isPassVisible ? "text" : "password"}
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <img
            src={isPassVisible ? view_icon : hide_icon}
            alt="eye"
            style={{ width: "25px", height: "27px", opacity: "0.6" }}
            onClick={changeVisibility}
          />
        </div>
        {action === "Sign Up" ? null : (
          <div className="forgot-password">
            Forgot Password? <span>Click Here!</span>
          </div>
        )}

        <div className="submit-container">
          {buttonAction.signup && (
            <div
              className={action === "Login" ? "submit grey" : "submit"}
              onClick={() => handleToggle("Sign Up")}
            >
              Sign Up
            </div>
          )}
          {buttonAction.loginup && (
            <div
              className={action === "Sign Up" ? "submit grey" : "submit"}
              onClick={() => handleToggle("Login")}
            >
              Login
            </div>
          )}
          {buttonAction.submitup && (
            <div className="submit" onClick={handleSubmit}>
              {action}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default LoginSignup;
