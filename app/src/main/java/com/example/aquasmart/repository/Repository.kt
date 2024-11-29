package com.example.aquasmart.repository

import com.example.aquasmart.models.Reports
import java.time.LocalDate

/**
 * Objeto repositorio que contiene una lista de objetos Reports.
 */
object Repository {

    val listReports: List<Reports> = listOf(
        Reports(
            "Report 1",
            "Client A",
            LocalDate.of(2024, 1, 15),
            "First project status update",
            "https://images.unsplash.com/photo-1576086639808-ddfd21aa668c?q=80&w=2680&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        Reports(
            "Report 2",
            "Client B",
            LocalDate.of(2024, 2, 10),
            "Monthly sales analysis",
            "https://images.unsplash.com/photo-1583912086005-ac9abca6c9db?q=80&w=2674&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        Reports(
            "Report 3",
            "Client C",
            LocalDate.of(2024, 3, 5),
            "Site inspection details",
            "https://images.unsplash.com/photo-1582719366794-4d27cd0a34a0?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        Reports(
            "Report 4",
            "Client D",
            LocalDate.of(2024, 4, 20),
            "Bug report for application",
            "https://images.unsplash.com/photo-1581594355548-aab613ebdb69?q=80&w=2594&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        Reports(
            "Report 5",
            "Client E",
            LocalDate.of(2024, 5, 15),
            "Quarterly performance review",
            "https://images.unsplash.com/photo-1576086213369-97a306d36557?q=80&w=2680&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        Reports(
            "Report 6",
            "Client F",
            LocalDate.of(2024, 6, 30),
            "Proposal for new project",
            "https://images.unsplash.com/photo-1582719471180-aad67c6023c8?q=80&w=2650&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        Reports(
            "Report 7",
            "Client G",
            LocalDate.of(2024, 7, 12),
            "Annual financial summary",
            "https://images.unsplash.com/photo-1579684256060-d5a308109e21?q=80&w=2680&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        Reports(
            "Report 8",
            "Client H",
            LocalDate.of(2024, 8, 25),
            "Technical audit findings",
            "https://images.unsplash.com/photo-1576086085526-0de1930a57c7?q=80&w=2680&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        Reports(
            "Report 9",
            "Client I",
            LocalDate.of(2024, 9, 18),
            "Marketing campaign analysis",
            "https://images.unsplash.com/photo-1581594294883-5109c202942f?q=80&w=2680&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        Reports(
            "Report 10",
            "Client J",
            LocalDate.of(2024, 10, 10),
            "Final deliverables report",
            "https://images.unsplash.com/photo-1631651367550-a9bc35f6d200?q=80&w=2561&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        )
    )
}