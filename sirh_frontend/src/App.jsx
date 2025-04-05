import {useEffect, useState} from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import axios from 'axios'

function App() {
    const [data, setData] = useState([])
    const [columns, setColumns] = useState([])
    const [selectedClass, setSelectedClass] = useState('employees')

    useEffect(() => {
        axios.get(`http://localhost:8080/${selectedClass}`)
            .then(response => {
                console.log(response.data)
                const sortedData = Array.isArray(response.data) ? response.data.sort((a, b) => a.id - b.id) : []
                setData(sortedData)
                if (sortedData.length > 0) {
                    setColumns(Object.keys(sortedData[0]))
                }
            })
            .catch(error => {
                console.error('Error :', error)
            })
    }, [selectedClass])

    const renderCell = (value) => {
        if (Array.isArray(value)) {
            return value.join(', ')
        } else if (typeof value === 'boolean') {
            return value ? 'True' : 'False'
        } else {
            return value
        }
    }

    return (
        <>
            <div className="flex items-center justify-center py-5">
                <a href="https://vite.dev" target="_blank">
                    <img src={viteLogo} className="logo" alt="Vite logo"/>
                </a>
                <a href="https://react.dev" target="_blank">
                    <img src={reactLogo} className="logo react" alt="React logo"/>
                </a>
            </div>
            <div className="h-screen flex flex-col items-center justify-center">
                <h1 className="text-5xl">SIRH</h1>
                <div className="mt-5">
                    <button onClick={() => setSelectedClass('employees')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Employees
                    </button>
                    <button onClick={() => setSelectedClass('evaluations')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Evaluations
                    </button>
                    <button onClick={() => setSelectedClass('feedbacks')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Feedbacks
                    </button>
                    <button onClick={() => setSelectedClass('leaves')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Leaves
                    </button>
                    <button onClick={() => setSelectedClass('objectives')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Objectives
                    </button>
                    <button onClick={() => setSelectedClass('positions')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Positions
                    </button>
                    <button onClick={() => setSelectedClass('skills')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Skills
                    </button>
                    <button onClick={() => setSelectedClass('teams')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Teams
                    </button>
                    <button onClick={() => setSelectedClass('trainings')}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2">Trainings
                    </button>
                </div>
                <table className="table-auto border-collapse border border-gray-400 mt-5">
                    <thead>
                    <tr>
                        {columns.map(column => (
                            <th key={column} className="border border-gray-300 px-4 py-2">{column}</th>
                        ))}
                    </tr>
                    </thead>
                    <tbody>
                    {data.map(item => (
                        <tr key={item.id}>
                            {columns.map(column => (
                                <td key={column}
                                    className="border border-gray-300 px-4 py-2">{renderCell(item[column])}</td>
                            ))}
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </>
    )
}

export default App