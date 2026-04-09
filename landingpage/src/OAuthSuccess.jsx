import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { routePath } from "./Routpath";

const OAuthSuccess = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const token = params.get("token");

  

    if (token && token !== "undefined") {
      localStorage.setItem("jwtToken", token);

      

      // small delay ensures storage before redirect
      setTimeout(() => {
        navigate(routePath.user);
      }, 100);
    } else {
      console.log("No token found, redirecting to login");
      navigate(routePath.login);
    }
  }, []);

  return <div>Processing Google login...</div>;
};

export default OAuthSuccess;