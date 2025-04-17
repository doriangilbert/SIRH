import React from 'react'
import reactLogo from '../assets/react.svg'
import viteLogo from '/vite.svg'

const Header = ({ employeeId, setEmployeeId, setCurrentPage }) => {
    const handleLogout = () => {
        localStorage.removeItem('employeeId')
        setEmployeeId('')
        setCurrentPage('login')
        alert('Logged out successfully.')
    }

    return (
        <header className="flex items-center justify-between p-5 bg-gray-200 z-10">
            <div className="flex items-center" onClick={() => setCurrentPage('main')} style={{ cursor: 'pointer' }}>
                <a href="https://vite.dev" target="_blank">
                    <img src={viteLogo} className="logo" alt="Logo Vite" />
                </a>
                <a href="https://react.dev" target="_blank">
                    <img src={reactLogo} className="logo react" alt="Logo React" />
                </a>
                <h1 className="text-3xl mr-4">SIRH</h1>
            </div>
            <div className="flex items-center">
                <button
                    onClick={() => setCurrentPage('main')}
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-4"
                >
                    Home
                </button>
            </div>
            <div className="flex items-center">
                {employeeId && (
                    <div className="flex items-center">
                        <span className="mr-4">Employee ID: {employeeId}</span>
                        <button
                            onClick={handleLogout}
                            className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                        >
                            Logout
                        </button>
                    </div>
                )}
            </div>
        </header>
    )
}

export default Header