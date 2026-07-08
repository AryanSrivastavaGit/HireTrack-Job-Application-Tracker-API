import React from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import HomeLayout from '../layout/HomeLayout'
import StartPage from '../pages/StartPage'
import LoginPage from '../pages/LoginPage'
import SignupPage from '../pages/SignupPage'

const AppRouter = () => {
  const router = createBrowserRouter([{

    path: '/',
    element: <HomeLayout></HomeLayout>,
    children: [
      {
        index: true,
        element: <StartPage></StartPage>
      },
      {
        path: 'login',
        element: <LoginPage></LoginPage>
      },
      {
        path: 'signup',
        element: <SignupPage></SignupPage>
      }
    ]
  }])
  return (
    <div>
      <RouterProvider router={router}></RouterProvider>
    </div>
  )
}

export default AppRouter