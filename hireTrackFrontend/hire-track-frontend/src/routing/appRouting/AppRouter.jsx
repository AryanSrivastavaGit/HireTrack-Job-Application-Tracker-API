import React from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import HomeLayout from '../layout/HomeLayout'
import StartPage from '../pages/StartPage'
import LoginPage from '../pages/LoginPage'
import SignupPage from '../pages/SignupPage'
import HomePage from '../pages/HomePage'
import NotePage from '../pages/NotePage'
import ImportantDatePage from '../pages/ImportantDatePage'
import HireTrackPage from '../pages/HireTrackPage'

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
        path: 'signup',
        element: <SignupPage></SignupPage>
      },
      {
        path: 'login',
        element: <LoginPage></LoginPage>
      },
      {
        path: 'homepage',
        element: <HomePage></HomePage>
      },
      {
        path: 'notepage',
        element: <NotePage></NotePage>
      },
      {
        path: 'importantDatePage',
        element: <ImportantDatePage></ImportantDatePage>
      },
      {
        path: 'addHreTrack',
        element: <HireTrackPage></HireTrackPage>
      }
    ]
  }])
  return (
    <RouterProvider router={router}></RouterProvider>
  )
}

export default AppRouter