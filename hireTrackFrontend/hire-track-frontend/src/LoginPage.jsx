import React, { useState } from 'react'

const LoginPage = () => {
    const [loginData, setLoginData] = useState({ email: "", password: "" })

    let handleChange = (e) =>{
        const {name, value} = e.target;
        setLoginData({...loginData, [name]:value});
        console.log(loginData);
    }

    let handleSubmit = (e) => {
        e.preventDefault();
        
        async function submitLoginData(params) {
            try {
                console.log(loginData);
                // const res = await axios.post('', loginData);
                // console.log(res);
            } catch (error) {
                console.log(error);
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
                <form action="" method="post" onSubmit={handleSubmit}>
                    <div>

                    <label htmlFor="email">Email</label>
                    <input type="email" id='email' name='email' onChange={handleChange} required minLength={3} />
                    </div>
                    <div>
                    <label htmlFor="password">Password</label>
                    <input type="password" id='password' name='password' onChange={handleChange} minLength={8} required />

                    </div>
                    <button type="submit">Login</button>
                </form>

                </div>
            </div>
        </div>
    )
}

export default LoginPage