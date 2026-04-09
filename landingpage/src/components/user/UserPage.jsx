import React, { useEffect, useState } from "react";
import { getMyProfile } from "../../Api";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { routePath } from "../../Routpath";

const UserPage = () => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

   const handleLogout = () => {
    localStorage.removeItem("jwtToken");
    navigate(routePath.login);
  };
  // 🔥 Fetch user data
  useEffect(() => {
    const token = localStorage.getItem("jwtToken");

    if (!token) {
      navigate(routePath.login);
      return;
    }

    getMyProfile()
      .then((res) => {
        setUser(res.data);
      })
      .catch((err) => {
        console.error(err);
        Swal.fire("Error", "Session expired. Please login again.", "error");
        handleLogout();
      });
  }, []);

  const styles = {
  container: {
    height: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    background: "linear-gradient(135deg, #667eea, #764ba2)",
  },
  card: {
    background: "#fff",
    padding: "30px",
    borderRadius: "16px",
    width: "350px",
    textAlign: "center",
    boxShadow: "0 10px 25px rgba(0,0,0,0.2)",
  },
  title: {
    marginBottom: "20px",
    color: "#333",
  },
  info: {
    textAlign: "left",
    marginBottom: "20px",
    color: "#555",
  },
  logoutBtn: {
    width: "100%",
    padding: "10px",
    backgroundColor: "#dc3545",
    color: "#fff",
    border: "none",
    borderRadius: "8px",
    cursor: "pointer",
    fontWeight: "bold",
  },
};

  // 🔥 Logout logic
 

  if (!user) return <div style={{ textAlign: "center" }}>Loading...</div>;

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <h2 style={styles.title}>👤 User Profile</h2>

        <div style={styles.info}>
          <p><strong>Name:</strong> {user.name}</p>
          <p><strong>Email:</strong> {user.email}</p>
          <p><strong>Provider:</strong> {user.provider}</p>
        </div>

        <button style={styles.logoutBtn} onClick={handleLogout}>
          Logout
        </button>
      </div>
    </div>
  );
};

export default UserPage;