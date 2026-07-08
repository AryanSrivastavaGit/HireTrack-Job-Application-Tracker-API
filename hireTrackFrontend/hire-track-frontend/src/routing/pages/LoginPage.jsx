import React, { useState } from 'react'
import axios from 'axios';

const LoginPage = () => {
    const [loginData, setLoginData] = useState({ email: "", password: "" });

    const [error, setError] = useState("");

    const [loading, setLoading] = useState(false);

    let handleChange = (e) => {
        setError("");
        const { name, value } = e.target;
        setLoginData({ ...loginData, [name]: value });
        console.log(loginData);
    }

    let handleSubmit = (e) => {
        e.preventDefault();

        async function submitLoginData() {

            setLoading(true);

            try {

                const loginDataSend = {
                    email: loginData.email,
                    password: loginData.password
                };

                console.log(loginData);
                const res = await axios.post('http://localhost:8080/auth/login', loginDataSend);
                console.log(res);

            } catch (error) {
                if (error.response) {
                    setError(error.response.data || "Something went wrong. Please try again.");
                } else {
                    setError("Network error. Please try again.");
                }
            }finally{
                setLoading(false);
            }
        }
        submitLoginData();
    }


    return (
        <div>
            <div className='loginPage'>
                <div className="loginPagediv">
                    <h1>Login</h1>
                    <p>Login to continue</p>
                    {error && <div className='loginErrorMessage'>{error}</div>}
                    <form action="" method="post" onSubmit={handleSubmit}>
                        <div>
                            <label htmlFor="email">Email</label>
                            <input type="email" id='email' name='email' onChange={handleChange} required minLength={3} />
                        </div>
                        <div>
                            <label htmlFor="password">Password</label>
                            <input type="password" id='password' name='password' onChange={handleChange} minLength={8} required />

                        </div>
                        <button type="submit">{loading ? "Loging in..." : "Login"}</button>
                    </form>

                </div>
            </div>
        </div>
    )
}

export default LoginPage