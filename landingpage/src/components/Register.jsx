import React, { useState } from "react";
import Swal from "sweetalert2";
import "../style/RegisterForm.css";
import { routePath } from "../Routpath";
import { registerUser } from "../Api";

const Register = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
  });

  const [validated, setValidated] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const form = e.target;

    // ✅ fixed validation
    if (!form.checkValidity()) {
      e.stopPropagation();
      setValidated(true);
      return;
    }

    setValidated(true);

    try {
      // 🔥 API call
      await registerUser({
        name: formData.name,
        email: formData.email,
        password: formData.password,
      });

      Swal.fire({
        title: "Registered!",
        text: "Your account has been created successfully.",
        icon: "success",
        confirmButtonText: "OK",
      });

      // ✅ reset form correctly
      setFormData({
        name: "",
        email: "",
        password: "",
      });

      setValidated(false);

    } catch (error) {
      console.error(error);

      Swal.fire({
        title: "Error",
        text: error.response?.data || "Registration failed",
        icon: "error",
      });
    }
  };

  return (
    <div className="register-page">
      <div className="container h-100">
        <div className="row justify-content-center align-items-center h-100">
          <div className="col-11 col-sm-8 col-md-6 m-5 col-lg-4">
            <div className="card shadow-sm register-card">
              <div className="card-body">
                <h3 className="mb-4 text-center">Create Account</h3>

                <form
                  noValidate
                  className={`needs-validation ${
                    validated ? "was-validated" : ""
                  }`}
                  onSubmit={handleSubmit}
                >
                  {/* Name */}
                  <div className="mb-3">
                    <label htmlFor="name" className="form-label">
                      Name
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="name"
                      name="name"
                      required
                      minLength={2}
                      value={formData.name}
                      onChange={handleChange}
                    />
                    <div className="invalid-feedback">
                      Please enter your name (at least 2 characters).
                    </div>
                  </div>

                  {/* Email */}
                  <div className="mb-3">
                    <label htmlFor="email" className="form-label">
                      Email
                    </label>
                    <input
                      type="email"
                      className="form-control"
                      id="email"
                      name="email"
                      required
                      value={formData.email}
                      onChange={handleChange}
                    />
                    <div className="invalid-feedback">
                      Please provide a valid email address.
                    </div>
                  </div>

                  {/* Password */}
                  <div className="mb-3">
                    <label htmlFor="password" className="form-label">
                      Password
                    </label>
                    <input
                      type="password"
                      className="form-control"
                      id="password"
                      name="password"
                      required
                      minLength={6}
                      value={formData.password}
                      onChange={handleChange}
                    />
                    <div className="invalid-feedback">
                      Please provide a password (min 6 characters).
                    </div>
                  </div>

                  <button className="btn btn-primary w-100" type="submit">
                    Sign Up
                  </button>

                  <p className="text-center mt-3 mb-0 small text-muted">
                    Already have an account?{" "}
                    <a href={routePath.login}>Log in</a>
                  </p>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;