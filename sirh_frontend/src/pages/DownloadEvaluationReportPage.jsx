import React, { useEffect, useState } from 'react'
import axios from 'axios'
import Header from '../components/Header.jsx'
import DataTable from '../components/DataTable.jsx'

const DownloadEvaluationReportPage = ({ employeeId, setEmployeeId, setCurrentPage }) => {
    const [evaluations, setEvaluations] = useState([])
    const [evaluationId, setEvaluationId] = useState('')
    const [error, setError] = useState(null)

    useEffect(() => {
        axios.get('http://localhost:8080/evaluations')
            .then((response) => {
                setEvaluations(response.data)
                setError(null)
            })
            .catch((err) => {
                setError(err.response?.data || 'An error occurred while fetching evaluations.')
            })
    }, [])

    const handleDownload = (e) => {
        e.preventDefault()
        if (!evaluationId) {
            setError('Please enter a valid evaluation ID.')
            return
        }

        axios.get(`http://localhost:8080/evaluations/${evaluationId}/report`, {
            responseType: 'blob',
        })
            .then((response) => {
                const url = window.URL.createObjectURL(new Blob([response.data]))
                const link = document.createElement('a')
                link.href = url
                link.setAttribute('download', `evaluation_report_${evaluationId}.pdf`)
                document.body.appendChild(link)
                link.click()
                link.remove()
                setError(null)
            })
            .catch((err) => {
                setError(err.response?.data || 'An error occurred while downloading the report.')
            })
    }

    return (
        <div className="h-screen flex flex-col">
            <Header employeeId={employeeId} setEmployeeId={setEmployeeId} setCurrentPage={setCurrentPage} />
            <div className="flex flex-col items-center justify-center">
                <h1 className="text-3xl mb-6">Download evaluation report</h1>
                {error && <div className="text-red-500 mb-4">{error}</div>}
                <form onSubmit={handleDownload} className="flex flex-col items-center">
                    <div className="mb-4">
                        <label htmlFor="evaluationId" className="block mb-2">Evaluation ID:</label>
                        <input
                            type="text"
                            id="evaluationId"
                            value={evaluationId}
                            onChange={(e) => setEvaluationId(e.target.value)}
                            placeholder="Evaluation ID"
                            className="border border-gray-300 rounded px-2 py-1"
                        />
                    </div>
                    <button
                        type="submit"
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                    >
                        Download report
                    </button>
                </form>
                <h2 className="text-2xl mt-6 mb-4">Available evaluations</h2>
                {evaluations.length === 0 ? (
                    <div>No evaluation available.</div>
                ) : (
                    <DataTable data={evaluations} />
                )}
                <button
                    onClick={() => setCurrentPage('main')}
                    className="mt-4 bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded"
                >
                    Back
                </button>
            </div>
        </div>
    )
}

export default DownloadEvaluationReportPage