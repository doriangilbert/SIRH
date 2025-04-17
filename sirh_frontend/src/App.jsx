import { useState, useEffect } from 'react'
import './App.css'
import LoginPage from './LoginPage'
import MainPage from './MainPage'
import DataPage from './DataPage'

function App() {
    const [employeeId, setEmployeeId] = useState(localStorage.getItem('employeeId') || '')
    const [currentPage, setCurrentPage] = useState('main')

    useEffect(() => {
        if (!employeeId) {
            setCurrentPage('login')
        }
    }, [employeeId])

    return (
        <div className="App">
            {currentPage === 'login' && (
                <LoginPage setEmployeeId={setEmployeeId} setCurrentPage={setCurrentPage} />
            )}
            {currentPage === 'main' && employeeId && (
                <MainPage
                    employeeId={employeeId}
                    setEmployeeId={setEmployeeId}
                    setCurrentPage={setCurrentPage}
                />
            )}
            {currentPage === 'data' && employeeId && (
                <DataPage
                    employeeId={employeeId}
                    setEmployeeId={setEmployeeId}
                    setCurrentPage={setCurrentPage}
                />
            )}
        </div>
    )
}

export default App