import React, { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../contextApi/AuthProvider';

const SignupPage = () => {

  const [signupData, setSignupData] = useState({
    fullName: "",
    email: "",
    password: "",
    confirmPasssword: ""
  })

  const [error, setError] = useState("");

  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  const {login} = useAuth();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setSignupData(prev => ({ ...signupData, [name]: value }));
    setError(""); // clear old error as user edits
  }

  const handleSubmit = (e) => {
    e.preventDefault();

    async function submitSignupData() {
      setError("");

      if (signupData.password !== signupData.confirmPasssword) {
        setError("Passwords do not match");
        return;
      }

      setLoading(true);

      try {

        const signupDataSend = {
          fullName: signupData.fullName,
          email: signupData.email,
          password: signupData.password
        };

        const res = await axios.post('http://localhost:8080/auth/register', signupDataSend);

        login(res.data);
        navigate('/homepage')

      } catch (err) {
        if (err.response) {
          setError(err.response.data || "Something went wrong. Please try again.");
        } else {
          setError("Network error. Please try again.");
        }
      } finally {
        setLoading(false);
      }
    }

    submitSignupData();
  }

  return (
    <div>
      <div className='signupPage'>
        <div className='signupPageDiv'>
          <form onSubmit={handleSubmit}>
            <h1>Sign up</h1>
            <p>Sign up to continue</p>
            {error && <div className='signupErrorMessage'> {error} </div>}

            <div>
              <label htmlFor="fullName">Full Name</label>
              <input type="text" id='fullName' name='fullName' onChange={handleChange} required minLength={3} />
            </div>
            <div>
              <label htmlFor="email">Email</label>
              <input type="email" id='email' name='email' onChange={handleChange} required />
            </div>
            <div>
              <label htmlFor="password">Password</label>
              <input type="password" id='password' name='password' onChange={handleChange} minLength={8} required />
            </div>
            <div>
              <label htmlFor="confirmPassword">Confirm Password</label>
              <input type="password" id='confirmPassword' name='confirmPasssword' onChange={handleChange} minLength={8} required />
            </div>
            <button type="submit" disabled={loading}>{loading ? "Signing up..." : "Sign up"}</button>
          </form>
        </div>
      </div>
    </div>
  )
}

export default SignupPage