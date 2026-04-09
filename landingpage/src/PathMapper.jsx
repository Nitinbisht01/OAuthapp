import React from 'react'
import {BrowserRouter,Routes,Route} from 'react-router-dom'
import App from './App'
import { routePath } from './Routpath'
import Register from './components/Register'
import Login from './components/Login'
import UserPage from './components/user/UserPage'
import OAuthSuccess from './OAuthSuccess'

const PathMapper = () => {
  return (
    <>
    <BrowserRouter>
    <Routes>
        <Route path={routePath.home} element={<App/>}></Route>
        <Route path={routePath.reg} element={<Register/>}></Route>
        <Route path={routePath.login} element={<Login/>}></Route>
        
    <Route path={routePath.oauth}  element={<OAuthSuccess />} />
        <Route path={routePath.user} element={<UserPage/>}></Route>npm run dev
    </Routes>
    </BrowserRouter>
    </>
  )
}

export default PathMapper