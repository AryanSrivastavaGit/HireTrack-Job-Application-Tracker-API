import React, { useState } from 'react'

const SignupPage = () => {

  const [signupData, setSignupData] = useState({
    name: "",
    email: "",
    password: "",
    confirmPasssword: ""
  })

  const handleChange = (e) => {
    const {name, value} = e.target;
    setSignupData({...signupData, [name]:value});
    console.log(signupData);
  }

  const handleSubmit = (e) => {
    
    e.preventDefault();

    async function submitSignupData() {
      try{
        console.log(signupData);
        // const res = await axios.post('', signupData);
        // console.log(res);
      }catch(error){
        console.log(error);
      }      
    }
    submitSignupData();
  }


  return (
    <div>
      <div className='signupPage'>
        <div className='signupPageDiv'>
          <form action="" method="post" onSubmit={handleSubmit}>
            <h1>Sign up</h1>
            <p>Sign up to continue</p>
            <div>
              <label htmlFor="fullName">Full Name</label>
              <input type="text" id='fullName' name='name' onChange={handleChange} required minLength={3} />
            </div>
            <div>
              <label htmlFor="email">Email</label>
              <input type="email" id='email' name='email' onChange={handleChange} required minLength={3} />
            </div>
            <div>
              <label htmlFor="password">Password</label>
              <input type="password" id='password' name='password' onChange={handleChange} minLength={8} required />
            </div>
            <div>
              <label htmlFor="confirmPassword">Confirm Password</label>
              <input type="password" id='confirmPassword' name='confirmPasssword' onChange={handleChange} minLength={8} required />
            </div>
            <button type="submit">Sign up</button>
          </form>
        </div>
      </div>
    </div>
  )
}

export default SignupPage