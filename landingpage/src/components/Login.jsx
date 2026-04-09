import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser } from '../Api'; // from api.js
import Swal from 'sweetalert2';
import { routePath } from '../Routpath';

const Login = () => {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    email: '',
    password: '',
  });

  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const res = await loginUser(form); // POST /auth/login
      
      const token = res.data.token;
      localStorage.setItem('jwtToken', token);

      await Swal.fire('Success', 'Login successful!', 'success');
      navigate('/user/userpage'); // redirect to user page
    } catch (err) {
      console.error(err);
      Swal.fire(
        'Error',
        err.response?.data?.message || 'Invalid email or password',
        'error'
      );
    } finally {
      setLoading(false);
    }
  };

 const handleGoogleLogin = () => {
  window.location.href = 'http://localhost:9091/oauth2/authorization/google';
};
const handleGithubLogin=()=>{
window.location.href = 'http://localhost:9091/oauth2/authorization/github';
}
  return (
    <div className="container py-5">
      <div className="row justify-content-center">
        <div className="col-md-5">
          <h3 className="mb-4 text-center">Login</h3>

          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label className="form-label">Email</label>
              <input
                type="email"
                name="email"
                className="form-control"
                value={form.email}
                onChange={handleChange}
                required
              />
            </div>

            <div className="mb-3">
              <label className="form-label">Password</label>
              <input
                type="password"
                name="password"
                className="form-control"
                value={form.password}
                onChange={handleChange}
                required
              />
            </div>

            <button
              type="submit"
              className="btn btn-primary w-100"
              disabled={loading}
            >
              {loading ? 'Logging in...' : 'Login'}
            </button>
          </form>

          <div className="text-center mt-3">
            <span>or</span>
          </div>

          <button
            className="btn btn-danger w-100 mt-2"
            onClick={handleGoogleLogin}
          >
            Continue with Google
          </button>

           <button
            className="btn btn-danger w-100 mt-2"
            onClick={handleGithubLogin}
          >
            Continue with Github
          </button>

          <p className="text-center mt-3">
            Don&apos;t have an account?{' '}
            <a href={routePath.reg}>Register</a>
          </p>
        </div>
      </div>
    </div>
  );
};

export default Login;