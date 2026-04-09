import React from "react";
import { useNavigate } from "react-router-dom";
import { routePath } from "./Routpath";

const Home = () => {
  const navigate = useNavigate();
  const styles = {
  container: {
    height: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    background: "linear-gradient(135deg, #1e3c72, #2a5298)",
  },
  card: {
    background: "#fff",
    padding: "40px",
    borderRadius: "16px",
    textAlign: "center",
    width: "400px",
    boxShadow: "0 10px 30px rgba(0,0,0,0.2)",
  },
  title: {
    fontSize: "26px",
    fontWeight: "bold",
    marginBottom: "15px",
    color: "#333",
  },
  subtitle: {
    fontSize: "14px",
    color: "#666",
    marginBottom: "30px",
  },
  buttonGroup: {
    display: "flex",
    gap: "15px",
    justifyContent: "center",
  },
  button: {
    padding: "10px 20px",
    border: "none",
    borderRadius: "8px",
    cursor: "pointer",
    fontWeight: "bold",
    fontSize: "14px",
    transition: "0.3s",
  },
  loginBtn: {
    backgroundColor: "#2a5298",
    color: "#fff",
  },
  registerBtn: {
    backgroundColor: "#28a745",
    color: "#fff",
  },
};

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <h1 style={styles.title}>Welcome to Your Authenticator App 🔐</h1>

        <p style={styles.subtitle}>
          Secure your identity. Login with confidence.  
          Manage access with ease.
        </p>

        <div style={styles.buttonGroup}>
          <button
            style={{ ...styles.button, ...styles.loginBtn }}
            onClick={() => navigate(routePath.login)}
          >
            Login
          </button>

          <button
            style={{ ...styles.button, ...styles.registerBtn }}
            onClick={() => navigate(routePath.reg)}
          >
            Register
          </button>
        </div>
      </div>
    </div>
  );
};

export default Home;