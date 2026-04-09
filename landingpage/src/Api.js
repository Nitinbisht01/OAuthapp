import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:9091",
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem("jwtToken");

  console.log("Sending Token:", token); // 🔥 DEBUG

  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});


export const registerUser = (data) =>
  api.post("/api/v1/auth/register", data);

export const loginUser = (data) =>
  api.post("/api/v1/auth/login", data);

// 👤 USER APIs
export const getMyProfile = () =>
  api.get("/api/v1/user/me");

export const getAllUsers = () =>
  api.get("/api/v1/user");

export default api;