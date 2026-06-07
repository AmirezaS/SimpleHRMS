package org.j2ee.simplehrms.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "performance_reviews")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReview {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private Employee reviewer;
 
    @Column(name = "review_period_start", nullable = false)
    private LocalDate reviewPeriodStart;
 
    @Column(name = "review_period_end", nullable = false)
    private LocalDate reviewPeriodEnd;
 
    @Column(name = "review_date")
    private LocalDate reviewDate;
 
    @Column(name = "review_year", nullable = false)
    private Integer reviewYear;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "review_type", length = 20)
    @Builder.Default
    private ReviewType reviewType = ReviewType.ANNUAL;
 
    // Scores (1-5 scale)
    @Column(name = "job_knowledge_score")
    private Integer jobKnowledgeScore;
 
    @Column(name = "work_quality_score")
    private Integer workQualityScore;
 
    @Column(name = "productivity_score")
    private Integer productivityScore;
 
    @Column(name = "teamwork_score")
    private Integer teamworkScore;
 
    @Column(name = "communication_score")
    private Integer communicationScore;
 
    @Column(name = "leadership_score")
    private Integer leadershipScore;
 
    @Column(name = "innovation_score")
    private Integer innovationScore;
 
    @Column(name = "overall_score")
    private Double overallScore;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "overall_rating", length = 30)
    private OverallRating overallRating;
 
    @Column(name = "strengths", length = 1000)
    private String strengths;
 
    @Column(name = "areas_for_improvement", length = 1000)
    private String areasForImprovement;
 
    @Column(name = "goals_for_next_period", length = 1000)
    private String goalsForNextPeriod;
 
    @Column(name = "reviewer_comments", length = 1000)
    private String reviewerComments;
 
    @Column(name = "employee_comments", length = 1000)
    private String employeeComments;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    @Builder.Default
    private ReviewStatus status = ReviewStatus.DRAFT;
 
    @Column(name = "salary_increase_recommended")
    private Boolean salaryIncreaseRecommended;
 
    @Column(name = "recommended_salary_increase_percent")
    private Double recommendedSalaryIncreasePercent;
 
    @Column(name = "promotion_recommended")
    @Builder.Default
    private Boolean promotionRecommended = false;
 
    public enum ReviewType { ANNUAL, SEMI_ANNUAL, QUARTERLY, PROBATION, SPECIAL }
 
    public enum OverallRating {
        EXCEPTIONAL, EXCEEDS_EXPECTATIONS, MEETS_EXPECTATIONS, NEEDS_IMPROVEMENT, UNSATISFACTORY
    }
 
    public enum ReviewStatus { DRAFT, SUBMITTED, ACKNOWLEDGED, COMPLETED }


}
