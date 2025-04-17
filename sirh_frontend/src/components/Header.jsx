import React from 'react'
import reactLogo from '../assets/react.svg'
import viteLogo from '/vite.svg'

const Header = ({ fetchData, handleInit }) => {
    return (
        <header className="flex items-center justify-between p-5 bg-gray-200 z-10">
            <div className="flex items-center">
                <a href="https://vite.dev" target="_blank">
                    <img src={viteLogo} className="logo" alt="Vite logo" />
                </a>
                <a href="https://react.dev" target="_blank">
                    <img src={reactLogo} className="logo react" alt="React logo" />
                </a>
            </div>
            <h1 className="text-3xl">SIRH</h1>
            <div>
                {['employees', 'evaluations', 'feedbacks', 'leaverequests', 'objectives', 'positions', 'skills', 'teams', 'trainings', 'trainingrequests', 'notifications'].map((item) => (
                    <button
                        key={item}
                        onClick={() => fetchData(item)}
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2"
                    >
                        {item.charAt(0).toUpperCase() + item.slice(1)}
                    </button>
                ))}
            </div>
            <button onClick={handleInit} className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded m-2">
                Init
            </button>
        </header>
    )
}

export default Header