import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
//import './index.css'
import App from './App.jsx'
import PathMapper from './PathMapper.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <PathMapper />
  </StrictMode>,
)
